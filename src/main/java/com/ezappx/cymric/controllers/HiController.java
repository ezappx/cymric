package com.ezappx.cymric.controllers;

import com.ezappx.cymric.services.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HiController {

    @Autowired
    private HiService hiService;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "ezappx") String name) {
        return hiService.hi(name);
    }
}
