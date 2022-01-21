package com.anilakdemir.todoapp.paylod;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:37
 */
@Data
public class TodosResponse {
    private Long id;
    private String content;
    private LocalDate createdDate;
    private LocalDate endDate;
    private Long userId;
    private String importanceLevel;
}
