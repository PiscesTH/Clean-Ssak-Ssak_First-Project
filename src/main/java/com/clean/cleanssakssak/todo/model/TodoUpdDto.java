package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "todo 수정 시 받아올 데이터")
public class TodoUpdDto {
    @Schema(title = "todo를 수정하고 싶은 유저의 PK")
    private int loginedUserId;
    @Schema(title = "해당 todo의 PK")
    private int todoId;
    @Schema(title = "수정된 부분이 포함된 내용")
    private String cleaning;
    @Schema(title = "수정된 날짜")
    private String doDay;
}
