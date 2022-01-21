package com.anilakdemir.todoapp.paylod;

/**
 * @author anilakdemir
 * @date 18 Aralık 2021 Cumartesi
 * @time 20:32
 */
import lombok.Data;

@Data
public class AuthResponse {

    String token;
    Long userId;
}
