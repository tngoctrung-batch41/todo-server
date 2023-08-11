package com.exercise.todo.repository;

import com.exercise.todo.model.Todo;
import com.exercise.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodoByUser(User user);
}
