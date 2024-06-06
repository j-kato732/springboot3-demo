package com.example.demo.mapper;

import com.example.demo.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {

  @Select("SELECT * FROM tasks")
  List<Task> findAll();

  @Select("SELECT * FROM tasks WHERE id = #{id}")
  Task findById(int id);

  @Insert("INSERT INTO tasks(description) VALUES(#{description})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insert(Task task);

  @Update("UPDATE tasks SET description = #{description} WHERE id = #{id}")
  void update(Task task);

  @Delete("DELETE FROM tasks WHERE id = #{id}")
  void delete(int id);
}
