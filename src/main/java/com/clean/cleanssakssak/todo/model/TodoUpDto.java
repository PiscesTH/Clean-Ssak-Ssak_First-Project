package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoUpDto {//todo 수정 시 받아올 데이터
    @Schema(name = "todo를 수정하고 싶은 유저의 PK")
    private int userId;
    @Schema(name = "해당 todo의 PK")
    private int todoId;
    @Schema(name = "수정된 부분이 포함된 내용")
    private String cleaning;
    @Schema(name = "수정된 날짜")
    private String doDay;
}
