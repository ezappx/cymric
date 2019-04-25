package com.ezappx.builder.controllers;

import com.ezappx.builder.services.HiService;
import com.ezappx.builder.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HiController {

    @Autowired
    private HiService hiService;

    @RequestMapping("/hi")
    public CommonResponse<String> hi(@RequestParam(value = "fileName", defaultValue = "ezappx") String name) {
        return new CommonResponse<>(HttpStatus.OK, CommonResponse.SUCCESS, hiService.hi(name));
    }
}
