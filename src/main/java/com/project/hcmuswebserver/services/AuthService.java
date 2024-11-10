package com.project.hcmuswebserver.services;

import com.project.hcmuswebserver.dtos.RegisterDto;
import com.project.hcmuswebserver.entities.User;
import com.project.hcmuswebserver.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository _userRepository;
    private final PasswordEncoder _encoder;
    private final AuthenticationManager _authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this._userRepository = userRepository;
        this._encoder = encoder;
        this._authenticationManager = authenticationManager;
    }
    
    public String register(@Valid RegisterDto registerDto) {
        String email = registerDto.getEmail();
        String password = registerDto.getPassword();
        User existedUser = _userRepository.findByEmail(email);
        if(existedUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        User newUSer = new User(email, _encoder.encode(password));
        _userRepository.save(newUSer);
        return "Register successfully";
    }

    public User authenticate(@Valid RegisterDto registerDto) {
        String email = registerDto.getEmail();
        String password = registerDto.getPassword();
        _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User existedUser = _userRepository.findByEmail(email);
        if(existedUser == null || !existedUser.verifyPassword(password, _encoder)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email or password");
        }
        return existedUser;
    }
}
