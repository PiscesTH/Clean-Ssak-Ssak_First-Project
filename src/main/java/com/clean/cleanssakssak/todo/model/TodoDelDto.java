package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoDelDto {//todo 삭제 시 필요한 데이터
    @Schema(name = "해당 todo PK")
    private int todoId;
    @Schema(name = "todo 삭제하고 싶은 유저의 PK")
    private int userId;
}
