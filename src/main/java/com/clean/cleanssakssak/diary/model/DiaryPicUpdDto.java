package com.clean.cleanssakssak.diary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "다이어리 수정 시 사진만 따로 UPDATE시 필요한 데이터")
public class DiaryPicUpdDto {
    @Schema(title = "로그인 한 유저 pk")
    private int loginedUserId;
    @Schema(title = "다이어리 pk")
    private int diaryId;
    @Schema(title = "다이어리 사진")
    private List<String> pics;
}
