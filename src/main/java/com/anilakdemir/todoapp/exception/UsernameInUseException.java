package com.anilakdemir.todoapp.exception;

import com.anilakdemir.todoapp.utils.AppConstants;
import lombok.Data;

/**
 * @author anilakdemir
 * @date 18 Aralık 2021 Cumartesi
 * @time 19:16
 */
@Data
public class UsernameInUseException extends RuntimeException{


    public UsernameInUseException(AppConstants message) {
        super(String.valueOf(message));
    }
}
