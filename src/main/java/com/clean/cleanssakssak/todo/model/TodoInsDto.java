package com.clean.cleanssakssak.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class TodoInsDto {
    @Schema(description = "로그인 한 유저의 userId")
    private int userId;
    @Schema(description = "청소 목표")
    private String cleaning;
    @Schema(description = "청소 할 날짜")
    private String dDay;
    @JsonIgnore
    @Schema(description = "체크 여부")
    private int isChecked;
    @JsonIgnore
    @Schema(description = "삭제 여부")
    private int isDel;
    @JsonIgnore
    private int todoId;
}
