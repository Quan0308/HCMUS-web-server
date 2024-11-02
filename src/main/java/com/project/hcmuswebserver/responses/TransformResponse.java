package com.project.hcmuswebserver.responses;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.logging.Logger;


public class TransformResponse implements ResponseBodyAdvice<Object> {
    private final Logger logger = Logger.getLogger(TransformResponse.class.getName());

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // Return true to apply this advice globally to all responses
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, org.springframework.http.server.ServerHttpRequest request,
                                  org.springframework.http.server.ServerHttpResponse response) {


        logger.info("Transforming response body: " + body.getClass().getName());

        if (body instanceof SuccessResponse || body instanceof ResponseEntity) {
            return body;
        }

        if (body instanceof String) {
            return new SuccessResponse(HttpStatus.OK, (String) body, null);
        }

        return new SuccessResponse(HttpStatus.OK, "Success", body);
    }
}
