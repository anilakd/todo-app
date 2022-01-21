package com.anilakdemir.todoapp.exception;

import com.anilakdemir.todoapp.utils.AppConstants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author anilakdemir
 * @date 17 Aralık 2021 Cuma
 * @time 22:30
 */
@Data
public class TodoNotFoundException extends RuntimeException{


    private static  String exception = "TODO NOT FOUND WITH ID : ";
    private String path;


    public TodoNotFoundException(AppConstants constant) {
        super(String.valueOf(constant));

    }
}
