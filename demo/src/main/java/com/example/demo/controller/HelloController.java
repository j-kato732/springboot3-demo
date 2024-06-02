package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@Controller
public class HelloController {
  @Autowired
  private TaskService taskService;

  @GetMapping("/")
  public String hello(Model model) {
    // タスクを取得
    List<Task> tasks = taskService.getTasks();

    // テンプレートエンジンにtask一覧を渡す
    model.addAttribute("tasks", tasks);
    return "index";
  }
}
