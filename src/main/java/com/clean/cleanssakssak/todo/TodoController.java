package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.todo.model.TodoInsDto;
import com.clean.cleanssakssak.todo.model.TodoSelAllDto;
import com.clean.cleanssakssak.todo.model.TodoSelAllVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;

    @Operation(summary = "청소 할 일 등록", description = "등록 성공 : todo_id 값 리턴 / 등록 실패 : 0 ")
    @PostMapping
    public ResVo postTodo(@RequestBody TodoInsDto dto){
        return service.postTodo(dto);
    }
    @Operation(summary = "청소 할 일 목록 불러오기", description = "8개씩 페이징 처리")
    @GetMapping
    public List<TodoSelAllVo> getTodoAll(TodoSelAllDto dto) {
        return service.getTodoAll(dto);
    }
}
