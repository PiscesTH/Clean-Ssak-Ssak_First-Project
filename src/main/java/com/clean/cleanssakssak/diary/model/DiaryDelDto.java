package com.clean.cleanssakssak.diary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(title = "다이어리 삭제 시 필요한 데이터")
public class DiaryDelDto {

    @Schema(title = "로그인 한 유저 pk")
    private int loginedUserId;
    @Schema(title = "다이어리 번호")
    private int diaryId;


}

