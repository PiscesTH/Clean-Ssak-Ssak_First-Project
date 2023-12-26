package com.clean.cleanssakssak.diary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class DiarySelOneVo {
    @Schema(title = "다이어리 pk")
    @JsonIgnore
    private int diaryId;
    @Schema(title = "다이어리 제목")
    private String title;
    @Schema(title = "다이어리 내용")
    private String contents;
    @Schema(title = "다이어리 사진")
    private List<String> pics;
}
