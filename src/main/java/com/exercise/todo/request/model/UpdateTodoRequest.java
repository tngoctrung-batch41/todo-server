package com.exercise.todo.request.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoRequest {
    private Long todo_Id;
    private String Todo;
    private Date updateDate;
}
