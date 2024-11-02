package com.project.hcmuswebserver.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class SuccessResponse<T> {
    private HttpStatusCode status;
    private String message;
    private T data;

    public SuccessResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public HttpStatusCode getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
