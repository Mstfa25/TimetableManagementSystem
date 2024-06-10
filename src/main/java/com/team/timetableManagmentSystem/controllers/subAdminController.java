package com.team.timetableManagmentSystem.controllers;

import com.team.timetableManagmentSystem.service.subAdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subAdmin")
public class subAdminController {

    @Autowired
    private subAdminService subadminService;

    boolean isSubAdmin(HttpSession session) {
        return session.getAttribute("role") != null
                && session.getAttribute("role").equals(2);
    }
}
