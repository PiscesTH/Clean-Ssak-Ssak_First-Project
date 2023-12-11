package com.clean.cleanssakssak.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserInsSignupDto {// 회원가입 시 필요한 고객 요청 데이터
    @JsonIgnore
    private int userId;// 회원가입 성공 시 응답할 user_id 값
    @Schema(name = "ID")
    private String uid;// ID
    @Schema(name = "PW")
    private String upw;// 비밀번호
    @Schema(name = "별명 / 성함")
    private String nickname;// 별명 / 성함
}
