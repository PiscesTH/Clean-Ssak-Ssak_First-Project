package com.clean.cleanssakssak.diary.model;

import com.clean.cleanssakssak.common.Const;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "다이어리 전체 조회 시 필요한 데이터/ 페이징 처리 포함")
public class DiarySelDto {
    @Schema(title = "로그인 한 유저 pk")
    private int loginedUserId;
    @Schema(title = "페이지")
    private int page;
/*
    @JsonIgnore
    private int startIdx;//
    @JsonIgnore
    private int rowCount = Const.DIARY_ROW_COUNT;

    public void setPage(int page) { //페이징처리
        this.startIdx = (page - 1) * rowCount;
    }
    */


}
