package com.clean.cleanssakssak.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "todo 수정 시 불러오기한 데이터")
public class TodoSelOneVo {
    @Schema(title = "청소 목표")
    private String cleaning;
    @Schema(title = "청소 할 날짜")
    private String doday;
}
