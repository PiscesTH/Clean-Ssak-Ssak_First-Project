package com.clean.cleanssakssak.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class TodoSelAllDto {
    @Schema(description = "로그인 한 유저 userId")
    private int loginedUserId;
    @Schema(description = "페이지")
    private int page;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;
}
