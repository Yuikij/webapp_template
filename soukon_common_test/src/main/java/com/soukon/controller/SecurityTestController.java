package com.soukon.controller;

import com.soukon.core.http.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @PostMapping("/test2")
    public ApiResponse<Object> test2(){
        return ApiResponse.success();
    }

}
