package com.project.hcmuswebserver.models;

import org.springframework.http.HttpStatusCode;

public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse(HttpStatusCode status, String message) {
        this.statusCode = status.value();
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}