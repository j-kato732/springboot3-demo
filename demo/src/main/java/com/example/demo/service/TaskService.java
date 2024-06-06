package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.mapper.TaskMapper;

@Service
public class TaskService {
  @Autowired
  private TaskMapper taskMapper;

  public List<Task> getTasks() {
    List<Task> tasks = taskMapper.findAll();
    return tasks;
  }

  public Integer addTask(Task task) {
    Integer id = taskMapper.insert(task);
    return id;
  }
}
