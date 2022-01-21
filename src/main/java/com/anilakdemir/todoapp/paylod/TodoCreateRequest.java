package com.anilakdemir.todoapp.paylod;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:57
 */

@Data
public class TodoCreateRequest {

    private String content;
    private LocalDate endDate;
    private String importanceLevel;
}
