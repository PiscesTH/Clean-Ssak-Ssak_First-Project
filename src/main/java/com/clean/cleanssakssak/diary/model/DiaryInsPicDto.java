package com.clean.cleanssakssak.diary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "다이어리 작성 시 사진만 따로 INSERT시 필요한 데이터")
public class DiaryInsPicDto {
    @Schema(title = "다이어리 번호")
    private int diaryId;
    @Schema(title = "사진")
    private List<String> pics;


}
