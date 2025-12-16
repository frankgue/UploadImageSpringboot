package com.gkfcsolution.uploadimagespringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2025 at 21:08
 * File: null.java
 * Project: spring-boot-file-upload
 *
 * @author Frank GUEKENG
 * @date 09/12/2025
 * @time 21:08
 */
//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fileName;
    private String filetype;
//    @Lob
    private byte[] data;
}
