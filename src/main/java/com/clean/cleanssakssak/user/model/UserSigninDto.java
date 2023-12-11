package com.clean.cleanssakssak.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserSigninDto {// 로그인 시 고객이 입력한 ID, PW 데이터
    @Schema(name = "ID")
    private String uid;
    @Schema(name = "비밀번호")
    private String upw;
}
