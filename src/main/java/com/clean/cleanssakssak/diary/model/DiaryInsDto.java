package com.clean.cleanssakssak.diary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(title = "다이어리 작성 시 필요한 데이터")
public class DiaryInsDto {
    @JsonIgnore
    private int diary_id;
    @Schema(title = "유저")
    private int loginedUserId;
    @Schema(title = "제목")
    private String title;
    @Schema(title = "내용")
    private String contents;
    @Schema(title = "사진")
    private List<String> pics;



}

