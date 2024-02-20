package com.example.demo.controller;

import com.example.demo.service.ProductUploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/check")
    public String checkGetApi(){
        return "Hello from get API!";
    }

    @PostMapping("/check/post")
    public String checkPostApi(@RequestParam("msg") String msg){
        return "Hello from POST API!" + msg;
    }

    //for uploading the SINGLE file to the File System
    @PostMapping("/single/file")
    public ResponseEntity<ProductUploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File("/Users/emmanuelabraham/Documents/2024/Backend Projects/Spring Boot Demo/demo/Sample Data Storage/" + fileName));
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(fileName)
                    .toUriString();
            ProductUploadResponse response = new ProductUploadResponse(fileName,
                    downloadUrl,
                    file.getContentType(),
                    file.getSize());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //for uploading the MULTIPLE file to the File system
    @PostMapping("/multiple/file")
    public ResponseEntity<List<ProductUploadResponse>> handleMultipleFilesUpload(@RequestParam("files") MultipartFile[] files) {
        List<ProductUploadResponse> responseList = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(new File("/Users/emmanuelabraham/Documents/2024/Backend Projects/Spring Boot Demo/demo/Sample Data Storage/" + fileName));
                String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(fileName)
                        .toUriString();
                ProductUploadResponse response = new ProductUploadResponse(fileName,
                        downloadUrl,
                        file.getContentType(),
                        file.getSize());
                responseList.add(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.ok(responseList);
    }
}
