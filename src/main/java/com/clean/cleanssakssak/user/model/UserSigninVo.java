package com.clean.cleanssakssak.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserSigninVo {
    @Schema(title = "실행 결과 값")
    private int result;
    @Schema(title =  "로그인 한 유저의 PK")
    private int userId;
    @Schema(title = "로그인 한 유저의 별명 / 성함")
    private String nickname;
}
