package com.clean.cleanssakssak.diary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "다이어리 사진 작업")
public class DiaryInsPicDto {
    @Schema(title = "다이어리 번호")
    private int diaryId;
    @Schema(title = "사진")
    private List<String> pics;


}
