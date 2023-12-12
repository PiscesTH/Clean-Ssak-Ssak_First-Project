package com.clean.cleanssakssak.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "로그인한 유저의 정보 수정 시 필요한 데이터")
public class UserUbdDto {
    @Schema(description = "로그인 한 유저 pk")
    private int loginedUserId;
    @Schema(description = "변경할 비밀번호")
    private String upw;
    @Schema(description = "변경할 닉네임")
    private String nickname;
}
