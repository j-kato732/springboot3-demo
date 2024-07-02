package com.example.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoHandlerFoundException.class)
  public String handlerNotFound(NoHandlerFoundException e, Model model) {
    model.addAttribute("errorCode", "404");
    model.addAttribute("errorTitle", "Page Not Found");
    model.addAttribute("errorMessage", "The page you are looking for does not exist.");
    return "error/404";
  }

  @ExceptionHandler(Exception.class)
  public String UnExpectedError(Exception e, Model model) {
    model.addAttribute("errorCode", "500");
    model.addAttribute("errorTitle", "Internal Server Error");
    model.addAttribute("errorMessage", "予期せぬエラーが発生しました。管理者へ問い合わせください。");
    return "error/500";
  }
}
