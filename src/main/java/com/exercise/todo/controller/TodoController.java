package com.exercise.todo.controller;

import com.exercise.todo.common.WrapResponseStatus;
import com.exercise.todo.request.model.CreateTodoRequest;
import com.exercise.todo.request.model.UpdateTodoRequest;
import com.exercise.todo.response.WrapResponse;
import com.exercise.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

//    Get all items of an user
    @GetMapping("/getall/{userId}")
    public WrapResponse getAlls(@PathVariable Long userId){
        return WrapResponse.ok(todoService.getAllTodo(userId));
    }

//      create a new todo
    @PostMapping("/create")
    public WrapResponse createTodo (@RequestBody CreateTodoRequest request){
        return WrapResponse.ok(todoService.createTodo(request));
    }

//    update an todo
    @PutMapping("/update/{id}")
    public WrapResponse updateTodo (@RequestBody UpdateTodoRequest request, @PathVariable Long id){
        return WrapResponse.ok(todoService.updateTodo(request,id));
    }

//    delete an todo
    @DeleteMapping("/del/{todoId}")
    public WrapResponse deleteTodo (@PathVariable Long todoId){
        todoService.deleteTodo(todoId);
        return WrapResponse.ok(WrapResponseStatus.SUCCESS);
    }

}
