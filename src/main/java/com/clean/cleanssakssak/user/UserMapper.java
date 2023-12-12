package com.clean.cleanssakssak.user;

import com.clean.cleanssakssak.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int selIdComparison (String uid);// ID 중복 확인용

    int insUserSignup(UserInsSignupDto dto);// 회원가입 정보 INSERT

    Integer selUserByNickname(String nickname);// 유저 회원가입/ 정보 수정 시 nickname 중복 체크

    String selSigninPw(UserSigninDto dto);// 로그인 성공 유/무를 위한 upw SELECT

    UserSigninVo selSignin(UserSigninDto dto);// 로그인 성공 시 해당 유저의 정보 SELECT

    int updUserUpw(UserUbdDto dto);// 유저 정보 수정 시 비밀번호 변경

    int updUserNickname(UserUbdDto dto);// 유저 정보 수정 시 nickname 변경

    int delUser(int loginedUserId);// 유저 정보 삭제
}
