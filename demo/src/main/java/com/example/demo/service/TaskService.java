package com.example.demo.service;

import java.util.List;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.exception.TaskDuplicationException;
import com.example.demo.mapper.TaskMapper;

@Service
public class TaskService {
  @Autowired
  private TaskMapper taskMapper;

  public List<Task> getTasks() {
    List<Task> tasks = taskMapper.findAll();
    return tasks;
  }

  public Task getTaskById(int id) {
    return taskMapper.findById(id);
  }

  public Integer addTask(Task task) {
    try {
      Integer id = taskMapper.insert(task);
      return id;
    } catch (Exception e) {
      Throwable cause = e.getCause();
      if (cause instanceof JdbcSQLIntegrityConstraintViolationException) {
        throw new TaskDuplicationException("そのタスクは存在します。", e);
      }
      throw new RuntimeException("予期せぬエラーが発生しました。", e);
    }
  }

  public void updateTask(Task task) {
    taskMapper.update(task);
  }

  public void deleteTask(int id) {
    taskMapper.delete(id);
  }
}
