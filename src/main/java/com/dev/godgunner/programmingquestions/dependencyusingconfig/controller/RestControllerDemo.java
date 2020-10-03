package com.dev.godgunner.programmingquestions.dependencyusingconfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerDemo {

    @GetMapping("/get/{name}")
    public String getMethod(@PathVariable String name){
        return name;
    }
}
