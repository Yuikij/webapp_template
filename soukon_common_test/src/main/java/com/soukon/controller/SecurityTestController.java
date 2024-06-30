package com.soukon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }


//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
}
