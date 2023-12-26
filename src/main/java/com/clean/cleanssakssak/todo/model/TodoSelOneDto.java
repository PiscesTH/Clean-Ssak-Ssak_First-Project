package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "todo 수정 시 불러오기에 사용 될 데이터")
public class TodoSelOneDto {
    @Schema(title = "로그인한 유저")
    private int loginedUserId;
    @Schema(title = "todo PK")
    private int todoId;
}
