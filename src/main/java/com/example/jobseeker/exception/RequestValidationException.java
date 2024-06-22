package com.example.jobseeker.exception;

import java.util.HashMap;
import java.util.Map;

public class RequestValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public RequestValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
