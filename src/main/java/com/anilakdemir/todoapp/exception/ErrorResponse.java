package com.anilakdemir.todoapp.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author anilakdemir
 * @date 18 Aralık 2021 Cumartesi
 * @time 00:31
 */
@Data
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
}
