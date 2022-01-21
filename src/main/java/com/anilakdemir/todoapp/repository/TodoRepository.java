package com.anilakdemir.todoapp.repository;

import com.anilakdemir.todoapp.model.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:28
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Sort sort, Long userId);
}
