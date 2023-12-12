package com.clean.cleanssakssak.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "회원가입 시 필요한 고객 요청 데이터")
public class UserInsSignupDto {
    @JsonIgnore
    private int userId;// 회원가입 성공 시 응답할 user_id 값
    @Schema(title = "ID")
    private String uid;// ID
    @Schema(title = "PW")
    private String upw;// 비밀번호
    @Schema(title = "별명 / 성함")
    private String nickname;// 별명 / 성함
}
