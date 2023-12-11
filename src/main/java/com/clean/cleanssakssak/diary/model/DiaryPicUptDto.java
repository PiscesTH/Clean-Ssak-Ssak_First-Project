package com.clean.cleanssakssak.diary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "다이어리 ")
public class DiaryPicUptDto {
    @Schema(title = "유저")
    private int userId;
    @Schema(title = "다이어리 번호")
    private int diaryId;
    @Schema(title = "사진")
    private List<String> pics;
}
