package com.anilakdemir.todoapp.repository;

import com.anilakdemir.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:48
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
