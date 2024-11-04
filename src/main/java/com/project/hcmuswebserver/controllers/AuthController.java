package com.project.hcmuswebserver.controllers;

import com.project.hcmuswebserver.dtos.RegisterDto;
import com.project.hcmuswebserver.models.LoginResponse;
import com.project.hcmuswebserver.models.SuccessResponse;
import com.project.hcmuswebserver.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class AuthController {
    private final AuthService _authService;

    public AuthController(AuthService authService) {
        this._authService = authService;
    }

    @PostMapping("/register")
    public SuccessResponse<String> register(@RequestBody @Valid RegisterDto registerDto) {
        String successMessage = _authService.register(registerDto);
        return new SuccessResponse<>(HttpStatus.OK, successMessage, null);
    }

    @PostMapping("/login")
    public SuccessResponse<LoginResponse> login(@RequestBody @Valid RegisterDto registerDto) {
        return new SuccessResponse<LoginResponse>(HttpStatus.OK, "Login successfully", _authService.authenticate(registerDto));
    }
}
