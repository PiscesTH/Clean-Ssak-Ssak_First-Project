package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "todo 삭제 시 필요한 데이터")
public class TodoDelDto {
    @Schema(title = "해당 todo PK")
    private int todoId;
    @Schema(title = "로그인 한 유저 pk")
    private int loginedUserId;
}
