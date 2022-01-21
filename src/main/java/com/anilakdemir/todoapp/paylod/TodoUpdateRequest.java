package com.anilakdemir.todoapp.paylod;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author anilakdemir
 * @date 17 Aralık 2021 Cuma
 * @time 22:24
 */

@Data
public class TodoUpdateRequest {

    private String content;
    private LocalDate endDate;
    private String importanceLevel;

}
