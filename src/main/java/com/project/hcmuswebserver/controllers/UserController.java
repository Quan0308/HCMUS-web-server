package com.project.hcmuswebserver.controllers;

import com.project.hcmuswebserver.entities.User;
import com.project.hcmuswebserver.models.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/profile")
    public SuccessResponse<User> authenticateProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        return new SuccessResponse<User>(HttpStatus.OK, "Get profile successfully", currentUser);
    }
}
