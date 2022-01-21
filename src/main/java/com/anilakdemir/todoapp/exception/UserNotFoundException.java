package com.anilakdemir.todoapp.exception;

import com.anilakdemir.todoapp.utils.AppConstants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author anilakdemir
 * @date 15 Aralık 2021 Çarşamba
 * @time 00:00
 */
@Data
public class UserNotFoundException extends RuntimeException{



    public UserNotFoundException(AppConstants constant) {
        super(String.valueOf(constant));

    }
}
