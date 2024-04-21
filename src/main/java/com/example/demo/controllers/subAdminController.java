
package com.example.demo.controllers;

import com.example.demo.service.subAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subAdmin")
public class subAdminController {

    @Autowired
    private subAdminService subadminService;
}
