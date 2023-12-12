package com.clean.cleanssakssak.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoSelAllDto {// todo 전체 조회 시 필요한 데이터
    @Schema(description = "로그인 한 유저 userId")
    private int loginedUserId;
    @Schema(description = "페이지")
    private int page;
    @JsonIgnore
    private int startIdx;// 페이지 당 처음 보이는 글의 index 역할
    @JsonIgnore
    private int rowCount;// 페이지 당 보이는 리스트의 전체 수
}
