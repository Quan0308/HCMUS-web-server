package com.project.hcmuswebserver.controllers;

import com.project.hcmuswebserver.dtos.RegisterDto;
import com.project.hcmuswebserver.entities.User;
import com.project.hcmuswebserver.models.LoginResponse;
import com.project.hcmuswebserver.models.SuccessResponse;
import com.project.hcmuswebserver.services.AuthService;
import com.project.hcmuswebserver.utils.JwtService;
import com.project.hcmuswebserver.utils.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService _authService;
    private final JwtService _jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this._authService = authService;
        this._jwtService = jwtService;
    }

    @PostMapping("/register")
    public SuccessResponse<String> register(@RequestBody @Valid RegisterDto registerDto) {
        String successMessage = _authService.register(registerDto);
        return new SuccessResponse<>(HttpStatus.OK, successMessage, null);
    }

    @PostMapping("/login")
    public SuccessResponse<LoginResponse> login(@RequestBody @Valid RegisterDto registerDto) {
        User authenticatedUser = _authService.authenticate(registerDto);
        String accessToken = _jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(accessToken);
        return new SuccessResponse<>(HttpStatus.OK, "Login successfully", loginResponse);
    }
}
