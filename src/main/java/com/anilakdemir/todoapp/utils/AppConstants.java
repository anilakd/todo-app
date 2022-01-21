package com.anilakdemir.todoapp.utils;

/**
 * @author anilakdemir
 * @date 18 Aralık 2021 Cumartesi
 * @time 02:31
 */
public enum AppConstants {

    TODO_NOT_FOUND("TODO NOT FOUND"),
    USER_NOT_FOUND("USER NOT FOUND"),
    USER_IS_ALREADY_IN_USE("USER IS ALREADY IN USE")

    ;

    private final String code;
    AppConstants(String code) {
        this.code= code ;
    }

    public String getCode(){
        return this.code;
    }
}
