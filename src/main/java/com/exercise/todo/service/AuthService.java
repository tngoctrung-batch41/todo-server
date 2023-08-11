package com.exercise.todo.service;

import com.exercise.todo.exception.NotFoundException;
import com.exercise.todo.exception.ServiceException;
import com.exercise.todo.model.User;
import com.exercise.todo.repository.UserRepository;
import com.exercise.todo.request.auth.LogInRequest;
import com.exercise.todo.request.auth.RegisterRequest;
import com.exercise.todo.response.auth.LogInResponse;
import com.exercise.todo.response.auth.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest request) {
        boolean userOld = userRepository.findByEmail(request.getEmail()).isPresent();
        if (userOld){
            throw new ServiceException("This email is used");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegisterResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public LogInResponse login(LogInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new NotFoundException("Invalid email"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new ServiceException("Password invalid");
        var jwtToken = jwtService.generateToken(user);
        return LogInResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public String getLoggedUserId() {
        String userIdString = SecurityContextHolder.getContext().getAuthentication().getName();
        return userIdString;
    }
}
