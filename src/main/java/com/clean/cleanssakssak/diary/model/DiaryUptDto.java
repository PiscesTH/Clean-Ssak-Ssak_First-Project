package com.clean.cleanssakssak.diary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "다이어리 수정 작업")
public class DiaryUptDto {
    @Schema(title = "유저")
    private int loginedUserId;
    @Schema(title = "다이어리 번호")
    private int diaryId;
    @Schema(title = "제목")
    private String title;
    @Schema(title = "내용")
    private String contents;
    @Schema(title = "사진")
    private List<String> pics;

}

