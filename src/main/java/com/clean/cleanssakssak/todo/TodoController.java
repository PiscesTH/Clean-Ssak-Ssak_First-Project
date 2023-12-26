package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.todo.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/todo")
@Tag(name = "todo API", description = "todo 관련 정보 처리")
public class TodoController {
    private final TodoService service;

    //---------------------------------------todo 등록-------------------------------------------
    @Operation(summary = "todo 일정 등록", description = """
            등록 성공 : todo_id 값 리턴<br> 
            등록 실패 : 0 <br> 
            cleaning 값이 제대로 안 들어온 경우 : -1<br>
            do_day 값이 제대로 안 들어온 경우 : -2""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping
    public ResVo postTodo(@RequestBody TodoInsDto dto) {
        return service.postTodo(dto);
    }

    //---------------------------------------todo 조회-------------------------------------------
    @Operation(summary = "todo 일정 목록 불러오기", description = "8개씩 페이징 처리")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping
    public List<TodoSelAllVo> getTodoAll(TodoSelAllDto dto) {
        return service.getTodoAll(dto);
    }

    //---------------------------------------todo 수정-------------------------------------------
    @Operation(summary = "todo 일정 수정" ,description = """
            일정 내용 수정<br>
            수정 성공 : 1<br>
            수정 실패 : 0<br>
            cleaning 값이 제대로 안 들어온 경우 : -1<br>
            do_day 값이 제대로 안 들어온 경우 : -2""")
    @Parameters(value = {
            @Parameter(name = "userId", description = "todo를 수정하고 싶은 유저의 PK"),
            @Parameter(name = "todoId", description = "해당 todo의 PK"),
            @Parameter(name = "cleaning", description = "수정된 부분이 포함된 내용"),
            @Parameter(name = "doDay", description = "수정된 날짜"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping
    public ResVo patchTodo(@RequestBody TodoUpdDto dto){
        return service.patchTodo(dto);
    }

    //-----------------------------------todo 수정 시 불러오기------------------------------------

    @Operation(summary = "todo 일정 수정 시 불러오기" ,description = "기존 내용 불러오기")
    @Parameters(value = {
            @Parameter(name = "todoId", description = "해당 todo의 PK"),
            @Parameter(name = "cleaning", description = "청소 목표 기존 내용"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/edit")
    public TodoSelOneVo getTodoForEdit(@RequestParam("todo_id") int todoId){
        return service.getTodoForEdit(todoId);
    }

    //---------------------------------------todo 삭제-------------------------------------------
    @Operation(summary = "todo 일정 삭제", description = "일정을 삭제<br>0: 수정 실패, <br>1: 수정 성공")
    @Parameters(value = {
            @Parameter(name = "userId", description = "todo를 삭제하고 싶은 유저의 PK"),
            @Parameter(name = "todoId", description = "해당 todo의 PK")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping
    public ResVo delTodo(TodoDelDto dto){
        return service.delTodo(dto);
    }

    //---------------------------------------todo 체크 처리-------------------------------------------
    @Operation(summary = "todo 체크 기능", description = """
    todo 체크 누르기/취소<br>
    체크 수정 실패 : 0<br>
    체크 성공 : 1<br>
    체크 해제 성공 : 2""")
    @Parameters(value = {
            @Parameter(name = "userId", description = "todo를 삭제하고 싶은 유저의 PK"),
            @Parameter(name = "todoId", description = "해당 todo의 PK")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/check")
    public ResVo toggleCheck(TodoToggleCheckDto dto){
        return service.toggleCheck(dto);
    }




}
