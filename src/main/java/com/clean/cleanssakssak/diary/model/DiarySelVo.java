package com.clean.cleanssakssak.diary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "다이어리 전체 조회 시 응답 데이터")
public class DiarySelVo {
    @Schema(title = "로그인 한 유저 pk")
    private int loginedUserId;
    @Schema(title = "다이어리 pk")
    private int diaryId;
    @Schema(title = "로그인 한 유저 닉네임")
    private String nickname;
    @Schema(title = "다이어리 제목")
    private String title;
    @Schema(title = "다이어리 내용")
    private String contents;
    @Schema(title = "다이어리 사진")
    private List<String> pics;
    @Schema(title = "다이어리 작성일")
    private String createdAt;

}
