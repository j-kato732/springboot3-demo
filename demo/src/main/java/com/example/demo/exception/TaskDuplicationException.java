package com.example.demo.exception;

public class TaskDuplicationException extends RuntimeException {
  public TaskDuplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
