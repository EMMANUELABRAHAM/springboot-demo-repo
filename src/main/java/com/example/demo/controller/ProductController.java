package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
