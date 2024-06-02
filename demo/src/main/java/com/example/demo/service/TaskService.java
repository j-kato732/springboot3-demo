package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Task;

@Service
public class TaskService {

  public List<Task> getTasks() {
    List<Task> tasks = new ArrayList<>();
    tasks.add(new Task(1, "task 1"));
    tasks.add(new Task(2, "task 2"));
    tasks.add(new Task(3, "task 3"));
    return tasks;
  }
}
