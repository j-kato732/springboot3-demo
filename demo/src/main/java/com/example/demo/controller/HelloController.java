package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    // フォーム入力のための空のタスクインスタンスを渡す
    model.addAttribute("task", new Task());
    // テンプレートエンジンにtask一覧を渡す
    model.addAttribute("tasks", tasks);
    return "index";
  }

  @PostMapping("/tasks")
  public String addTask(@ModelAttribute Task task, HttpServletRequest request) {
    taskService.addTask(task);

    // 動的にリダイレクトURLを取得
    String redirectUrl = getBaseUrl(request) + "/";
    return "redirect:" + redirectUrl;
  }

  private String getBaseUrl(HttpServletRequest request) {
    String scheme = request.getHeader("X-Forwarded-Proto");
    if (scheme == null) {
      scheme = request.getScheme();
    }

    String serverName = request.getHeader("X-Forwarded-Host");
    if (serverName == null) {
      serverName = request.getServerName();
    }

    String contextPath = request.getContextPath();

    // ベースURLを生成
    return scheme + "://" + serverName + contextPath;
  }
}
