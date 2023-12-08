package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.todo.model.TodoInsDto;
import com.clean.cleanssakssak.todo.model.TodoSelAllDto;
import com.clean.cleanssakssak.todo.model.TodoSelAllVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int insTodo(TodoInsDto dto);
    List<TodoSelAllVo> selTodoAll(TodoSelAllDto dto);
}
