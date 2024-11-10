package com.project.hcmuswebserver.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;


    public String getAccessToken() {
        return accessToken;
    }
}
