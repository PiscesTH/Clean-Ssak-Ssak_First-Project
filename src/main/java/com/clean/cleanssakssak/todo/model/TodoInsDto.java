package com.clean.cleanssakssak.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoInsDto {
    @Schema(description = "로그인 한 유저의 userId")
    private int loginedUserId;
    @Schema(description = "청소 목표")
    private String cleaning;
    @Schema(description = "청소 할 날짜")
    private String doDay;
    @JsonIgnore
    private int todoId;
}
