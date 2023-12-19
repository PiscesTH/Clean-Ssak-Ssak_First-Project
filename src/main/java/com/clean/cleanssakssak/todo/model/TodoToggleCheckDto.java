package com.clean.cleanssakssak.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "todo 체크 시 필요한 데이터")
public class TodoToggleCheckDto {
    @Schema(title = "todo를 삭제하고 싶은 유저의 PK")
    private int todoId;
    @Schema(title = "해당 todo의 PK")
    private int loginedUserId;
    @JsonIgnore
    private int check; // 체크 용
}
