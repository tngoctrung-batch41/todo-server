package com.exercise.todo.service;

import com.exercise.todo.common.MapUlti;
import com.exercise.todo.exception.NotFoundException;
import com.exercise.todo.model.Todo;
import com.exercise.todo.model.User;
import com.exercise.todo.repository.TodoRepository;
import com.exercise.todo.repository.UserRepository;
import com.exercise.todo.request.model.CreateTodoRequest;
import com.exercise.todo.request.model.UpdateTodoRequest;
import com.exercise.todo.response.model.CreateTodoResponse;
import com.exercise.todo.response.model.ToDoResponse;
import com.exercise.todo.response.model.UpdateTodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final AuthService authService;
    public List<ToDoResponse> getAllTodo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User Not Found"));
        List<Todo> todos = todoRepository.findTodoByUser(user);
        List<ToDoResponse> res = new ArrayList<>();
        if(todos.size()==0){
            throw new NotFoundException("There is no todo");
        }
        for (Todo todo:todos
             ) {
            ToDoResponse toDoResponse = MapUlti.mapObject(todo, ToDoResponse.class);
            res.add(toDoResponse);
        }
        return res;
    }

    public ToDoResponse createTodo(CreateTodoRequest request) {
        Todo todo = new Todo();
        User user = userRepository.findByEmail(authService.getLoggedUserId()).get();
        todo.setTodo(request.getTodo());
        todo.setUser(user);
        todoRepository.save(todo);
        ToDoResponse response = MapUlti.mapObject(todo, ToDoResponse.class);
        return response;
    }

    public ToDoResponse updateTodo(UpdateTodoRequest request,Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->new NotFoundException("Cannot find the todo with id: " + id));
        todo.setTodo(request.getTodo());
        todoRepository.save(todo);
        ToDoResponse response = MapUlti.mapObject(todo,ToDoResponse.class);
        return response;
    }

    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NotFoundException("Not Found todo with id: " + todoId));
        todoRepository.delete(todo);
    }
}
