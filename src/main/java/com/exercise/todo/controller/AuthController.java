package com.exercise.todo.controller;

import com.exercise.todo.request.auth.LogInRequest;
import com.exercise.todo.request.auth.RegisterRequest;
import com.exercise.todo.response.WrapResponse;
import com.exercise.todo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public WrapResponse register (@RequestBody RegisterRequest request){
        return WrapResponse.ok(authService.register(request));
    }

    @PostMapping("/login")
    public WrapResponse login (@RequestBody LogInRequest request){
        return WrapResponse.ok(authService.login(request));
    }
}
