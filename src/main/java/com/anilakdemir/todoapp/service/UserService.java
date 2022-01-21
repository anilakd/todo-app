package com.anilakdemir.todoapp.service;

import com.anilakdemir.todoapp.exception.UserNotFoundException;
import com.anilakdemir.todoapp.exception.UsernameInUseException;
import com.anilakdemir.todoapp.model.User;
import com.anilakdemir.todoapp.paylod.UserCreateRequest;
import com.anilakdemir.todoapp.repository.UserRepository;
import com.anilakdemir.todoapp.utils.AppConstants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:49
 */

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(UserCreateRequest userCreateRequest){
        User inDBUser= this.userRepository.findByUsername(userCreateRequest.getUsername());
        if(inDBUser==null){
            User user = mapToUser(userCreateRequest);
            this.userRepository.save(user);
            return "User created";
        }else{
            throw new UsernameInUseException(AppConstants.USER_IS_ALREADY_IN_USE);
        }

    }

    public User findById(Long id){
        return this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(AppConstants.USER_NOT_FOUND));
    }

    private User mapToUser(UserCreateRequest userCreateRequest){
        User user = new User();
        user.setUsername(userCreateRequest.getUsername());
        System.out.println(passwordEncoder.encode(userCreateRequest.getPassword()));
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        return  user;
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void deleteUserById(Long userId) {
        User user = findById(userId);
        if(user!=null)
            this.userRepository.deleteById(userId);
    }
}
