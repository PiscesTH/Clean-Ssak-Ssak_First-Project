package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.todo.model.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {

    int insTodo(TodoInsDto dto);// todo 내용 작성

    List<TodoSelAllVo> selTodoAll(TodoSelAllDto dto);// todo 리스트 전체 조회

    int updTodo(TodoUpdDto dto);// todo 내용 수정

    TodoSelOneVo selTodoOne(TodoSelOneDto dto);// todo 내용 수정 시 불러올 원래 리스트

    int delTodo(TodoDelDto dto);// todo 삭제

    Integer selCheck(TodoToggleCheckDto dto);//todo check 유/무 확인

    int upCheck(TodoToggleCheckDto dto);//todo check toggle 처리

    int delTodoForUnregister(int loginedUserId);// todo 유저 탈퇴 시 해당 유저의 todo 전체 삭제
}
