package com.anilakdemir.todoapp.service;

import com.anilakdemir.todoapp.exception.TodoNotFoundException;
import com.anilakdemir.todoapp.model.ImportanceLevel;
import com.anilakdemir.todoapp.model.Todo;
import com.anilakdemir.todoapp.model.User;
import com.anilakdemir.todoapp.paylod.TodoCreateRequest;
import com.anilakdemir.todoapp.paylod.TodoUpdateRequest;
import com.anilakdemir.todoapp.paylod.TodosResponse;
import com.anilakdemir.todoapp.repository.TodoRepository;
import com.anilakdemir.todoapp.utils.AppConstants;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:29
 */
@Service
public class TodosService {
    private final TodoRepository todoRepository;
    private final UserService usersService;

    public TodosService(TodoRepository todoRepository, UserService usersService) {
        this.todoRepository = todoRepository;
        this.usersService = usersService;
    }

    public List<TodosResponse> getAllTodosByUserId(Long userId, String sortBy, String sortDir) {

        //IF DOES NOT EXIST, THIS LINE THROWS USERNOTFOUND EXCEPTION
        this.usersService.findById(userId);

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<Todo> todos = this.todoRepository.findByUserId(sort, userId);
        List<TodosResponse> todosResponses = todos.stream().map((post)->mapToResponse(post)).collect(Collectors.toList());
        return todosResponses;
    }

    public TodosResponse createTodo(Long userId, TodoCreateRequest todoCreateRequest) {
        User user = this.usersService.findById(userId);
        Todo todo = mapToTodo(todoCreateRequest);
        todo.setUser(user);
        System.out.println(todoCreateRequest.getImportanceLevel());
        ImportanceLevel importanceLevel = ImportanceLevel.valueOf(String.valueOf(todoCreateRequest.getImportanceLevel()));
        System.out.println(importanceLevel);
        todo.setImportanceLevel(ImportanceLevel.valueOf(todoCreateRequest.getImportanceLevel()));
        this.todoRepository.save(todo);

        TodosResponse todosResponse = mapToResponse(todo);
        return  todosResponse;
    }

    public TodosResponse updateTodo(Long todoId, TodoUpdateRequest todoUpdateRequest) {
        Todo todo = this.todoRepository.findById(todoId).orElseThrow(()->new TodoNotFoundException(AppConstants.TODO_NOT_FOUND));
        todo.setContent(todoUpdateRequest.getContent());
        todo.setEndDate(todoUpdateRequest.getEndDate());
        todo.setImportanceLevel(ImportanceLevel.valueOf(todoUpdateRequest.getImportanceLevel()));
        this.todoRepository.save(todo);
        return mapToResponse(todo);
    }

    private TodosResponse mapToResponse(Todo todo){
        TodosResponse todosResponse = new TodosResponse();
        todosResponse.setContent(todo.getContent());
        todosResponse.setCreatedDate(todo.getCreatedDate());
        todosResponse.setUserId(todo.getUser().getId());
        todosResponse.setId(todo.getId());
        todosResponse.setImportanceLevel(String.valueOf(todo.getImportanceLevel()));
        todosResponse.setEndDate(todo.getEndDate());

        return todosResponse;
    }

    private Todo mapToTodo(TodoCreateRequest todoCreateRequest){
        Todo todo = new Todo();
        todo.setContent(todoCreateRequest.getContent());
        todo.setEndDate(todoCreateRequest.getEndDate());
        todo.setImportanceLevel(ImportanceLevel.valueOf(todoCreateRequest.getImportanceLevel()));
        return todo;
    }

    public TodosResponse getById(Long todoId) {
        Todo todo = this.todoRepository.findById(todoId).orElseThrow(()->new TodoNotFoundException(AppConstants.TODO_NOT_FOUND));
        return mapToResponse(todo);
    }

    public void deleteTodoById(Long todoId) {
        this.todoRepository.deleteById(todoId);
    }

}
