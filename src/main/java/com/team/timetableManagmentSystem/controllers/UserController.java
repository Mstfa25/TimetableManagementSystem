package com.team.timetableManagmentSystem.controllers;

import com.team.timetableManagmentSystem.DTOs.user;
import com.team.timetableManagmentSystem.service.userServices;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "localhost:4200", originPatterns = "*")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private userServices userService;

    @CrossOrigin(allowCredentials = "true", origins = "localhost:4200", originPatterns = "*")
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody user user, HttpSession session) {
        System.out.println("\t" + user.getUsername());

        int userRole;
        userRole = userService.userRole(user.getUsername(), user.getPassword());

        if (userRole == -1) {
            ArrayList<String> s = new ArrayList<>();
            s.add("Invalid Credentials");

            return new ResponseEntity<>(s, HttpStatus.UNAUTHORIZED);
        }

        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", userRole);
        if (userRole == 2) {

        }
        ArrayList<String> s = new ArrayList<>();
        s.add("Login Successful");
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @CrossOrigin(allowCredentials = "true", origins = "localhost:4200", originPatterns = "*")
    @RequestMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        // Remove user information from the session
        session.removeAttribute("username");
        ArrayList<String> s = new ArrayList<>();
        s.add("Logout Successful");
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @CrossOrigin(allowCredentials = "true", originPatterns = "*", origins = "localhost:4200")
    @RequestMapping("/home")
    public ResponseEntity<?> home(HttpSession session) {
        System.out.println("enterd");
        if (session.getAttribute("username") != null) {
            System.out.println(session.getAttribute("username"));
            ArrayList<String> s = new ArrayList<>();
            s.add("home");
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            ArrayList<String> s = new ArrayList<>();
            s.add("login first");
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
    }

}
