package com.clean.cleanssakssak.diary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "다이어리 사진만 따로 INSERT할 때 사용할 중간 처리용 데이터")
public class DiaryInsPicProcDto {
    @Schema(title = "다이어리 번호")
    private int diaryId;
    @Schema(title = "사진")
    private List<String> pics;


}
