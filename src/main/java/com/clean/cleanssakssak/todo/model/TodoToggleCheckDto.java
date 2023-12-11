package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoToggleCheckDto {
    @Schema(name = "todo를 삭제하고 싶은 유저의 PK")
    private int todoId;
    @Schema(name = "해당 todo의 PK")
    private int userId;
}
