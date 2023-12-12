package com.clean.cleanssakssak.diary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "다이어리 삭제 작업")
public class DiaryDelDto {
    @Schema(title = "유저")
    private int loginedUserId;
    @Schema(title = "다이어리 번호")
    private int diaryId;


}

