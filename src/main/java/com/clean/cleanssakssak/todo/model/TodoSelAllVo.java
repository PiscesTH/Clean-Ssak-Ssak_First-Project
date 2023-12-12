package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoSelAllVo {// todo 전체 조회 시 응답할 전체 리스트 데이터
    @Schema(description = "할 일 pk")
    private int todoId;
    @Schema(description = "청소 할 일")
    private String cleaning;
    @Schema(description = "청소 할 날짜")
    private String doDay;
    @Schema(description = "체크 여부")
    private int isChecked;
}
