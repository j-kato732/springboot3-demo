package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@Controller
public class HelloController {

  @Autowired
  private TaskService taskService;

  @GetMapping("/")
  public String hello(Model model) {
    // テンプレートエンジンにtask一覧を渡す
    prepareModel(model, new Task(), null);

    return "index";
  }

  @PostMapping("/tasks")
  public String addTask(@Valid @ModelAttribute Task task, BindingResult result, HttpServletRequest request,
      Model model) {

    if (result.hasErrors()) {
      prepareModel(model, task, result);
      return "index";
    }

    taskService.addTask(task);

    // CodeSpaceではredirect:/で正しく動かないので、動的にURLを取得してリダイレクトする。
    String redirectUrl = getBaseUrl(request) + "/";
    return "redirect:" + redirectUrl;
  }

  @GetMapping("/tasks/edit/{id}")
  public String editTask(Model model, @PathVariable("id") int id) {
    Task task = taskService.getTaskById(id);
    model.addAttribute("task", task);

    return "edit";
  }

  @PostMapping("/tasks/edit")
  public String editTask(@Valid @ModelAttribute Task task, BindingResult result, Model model,
      HttpServletRequest request) {
    if (result.hasErrors()) {
      model.addAttribute("task", task);
      model.addAttribute("org.springframework.validation.BindingResult.task", result);

      return "edit";
    }

    taskService.updateTask(task);

    String redirectUrl = getBaseUrl(request) + "/";
    return "redirect:" + redirectUrl;
  }

  @PostMapping("/tasks/delete")
  public String deleteTask(@ModelAttribute Task task, HttpServletRequest request) {
    taskService.deleteTask(task.getId());

    // 動的にリダイレクトURLを取得
    String redirectUrl = getBaseUrl(request) + "/";
    return "redirect:" + redirectUrl;
  }

  private void prepareModel(Model model, Task task, BindingResult result) {
    List<Task> tasks = taskService.getTasks();
    model.addAttribute("tasks", tasks);

    if (result != null) {
      model.addAttribute("org.springframework.validation.BindingResult.task", result);
    }

    model.addAttribute("task", task);
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
