package com.gkfcsolution.uploadimagespringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * Created on 2025 at 22:11
 * File: null.java
 * Project: UploadImageSpringboot
 *
 * @author Frank GUEKENG
 * @date 15/12/2025
 * @time 22:11
 */
@RestController
@Slf4j
public class FileImageUploadController {


    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file")MultipartFile uploadFile) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(uploadFile.getOriginalFilename()));

        File folder = new File(uploadDir);

        // Si le dossier n'existe pas, on le crée
        if (!folder.exists()) {
            folder.mkdirs();
            log.info("Upload folder created at {}", folder.getAbsolutePath());
        }

        File file = new File(folder, fileName);

        if (file.exists()) {
            log.warn("File already exists: {}", file.getAbsolutePath());
        }

        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
            stream.write(uploadFile.getBytes());
        } catch (Exception e) {
            log.error("Error saving file: {}", e.getMessage());
            return "File upload failed";
        }

        log.info("File saved successfully: {}", file.getAbsolutePath());

        return "File uploaded successfully";
    }

}
