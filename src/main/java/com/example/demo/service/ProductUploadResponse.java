package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUploadResponse {
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private long fileSize;
}
