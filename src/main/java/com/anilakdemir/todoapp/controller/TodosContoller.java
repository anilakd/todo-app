package com.anilakdemir.todoapp.controller;

import com.anilakdemir.todoapp.model.Todo;
import com.anilakdemir.todoapp.paylod.TodoCreateRequest;
import com.anilakdemir.todoapp.paylod.TodoUpdateRequest;
import com.anilakdemir.todoapp.paylod.TodosResponse;
import com.anilakdemir.todoapp.service.TodosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:30
 */

@RestController
@RequestMapping("/todos")
public class TodosContoller {

    private final TodosService todosService;

    public TodosContoller(TodosService todosService) {
        this.todosService = todosService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<TodosResponse>> getAllTodos(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir ){
        return new ResponseEntity<>(this.todosService.getAllTodosByUserId(userId,sortBy,sortDir), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TodosResponse> createTodo(@PathVariable(name = "userId") Long userId,
                                                    @RequestBody TodoCreateRequest todoCreateRequest){
        return new ResponseEntity<>(this.todosService.createTodo(userId,todoCreateRequest ),HttpStatus.CREATED);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodosResponse> updateTodo(@PathVariable(name = "todoId") Long todoId ,
                                                    @RequestBody TodoUpdateRequest todoUpdateRequest){
        return new ResponseEntity<>(this.todosService.updateTodo(todoId, todoUpdateRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable(name = "todoId") Long todoId){
        this.todosService.deleteTodoById(todoId);
        return ResponseEntity.ok("Succesfully deleted");
    }

}
