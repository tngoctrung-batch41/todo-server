package com.exercise.todo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_todos")
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    private String todo;
    private Date addDate = new Date(2000,01,01);
    private Date complateDate = new Date(2000,01,01);
    private boolean isCompleted = false;
    @ManyToOne
    @JoinColumn(name="user_Id", nullable=false)
    private User user;


}
