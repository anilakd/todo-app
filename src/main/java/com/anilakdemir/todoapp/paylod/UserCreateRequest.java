package com.anilakdemir.todoapp.paylod;

import lombok.Data;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:50
 */
@Data
public class UserCreateRequest {

    private String username;
    private String password;
}
