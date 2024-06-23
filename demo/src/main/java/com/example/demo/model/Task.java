package com.example.demo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

  private int id;

  @Size(min = 1, max = 100, message = "タスクは1文字以上100文字以下で入力してください。")
  private String description;
}
