package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.todo.model.*;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {

    int upTodo(TodoUpDto dto);// todo 내용 수정

    int delTodo(TodoDelDto dto);// todo 삭제

    int selCheck(TodoToggleCheckDto dto);//todo check 유/무 확인

    int upCheck(int check);//todo check toggle 처리
}
