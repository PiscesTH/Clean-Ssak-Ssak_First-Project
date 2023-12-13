package com.clean.cleanssakssak.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(title = "응답용 클래스")
public class ResVo {// 응답에 쓰일 클래스
    private int result;
}
