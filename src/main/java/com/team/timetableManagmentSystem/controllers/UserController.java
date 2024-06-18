package com.team.timetableManagmentSystem.controllers;

import com.team.timetableManagmentSystem.DTOs.Branch;
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
        int[] userRole;
        System.out.println(user.getUsername());
        userRole = userService.userRole(user.getUsername(), user.getPassword());
        if (userRole[0] == -1) {
            ArrayList<String> s = new ArrayList<>();
            s.add("Invalid Credentials");
            return new ResponseEntity<>(s, HttpStatus.UNAUTHORIZED);
        }
        session.setAttribute("userId", userRole[0]);
        session.setAttribute("role", userRole[1]);
        if (userRole[1] == 2) {
            ArrayList<Branch> branchs = (ArrayList<Branch>) userService.getSubAdminBranches(userRole[0]);
            session.setAttribute("branchs", branchs);
        }
        ArrayList<String> s = new ArrayList<>();
        s.add("Login Successful");
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @CrossOrigin(allowCredentials = "true", origins = "localhost:4200", originPatterns = "*")
    @RequestMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        // Remove user information from the session
        session.removeAttribute("userId");
        session.removeAttribute("role");
        ArrayList<String> s = new ArrayList<>();
        s.add("Logout Successful");
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @CrossOrigin(allowCredentials = "true", originPatterns = "*", origins = "localhost:4200")
    @RequestMapping("/home")
    public ResponseEntity<?> home(HttpSession session) {
        System.out.println(session.getAttribute("userId"));
        if (session.getAttribute("userId") != null) {
            if (session.getAttribute("role").equals(1)) {
                ArrayList<String> s = new ArrayList<>();
                s.add("home");
                return new ResponseEntity<>(s, HttpStatus.OK);
            } else if (session.getAttribute("role").equals(2)) {
                ArrayList<String> s = new ArrayList<>();
                s.add("SubAdminHome");
                return new ResponseEntity<>(s, HttpStatus.OK);
            }
        }

        ArrayList<String> s = new ArrayList<>();
        s.add("login first");
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
