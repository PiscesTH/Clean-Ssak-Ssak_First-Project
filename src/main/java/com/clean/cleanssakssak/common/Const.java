package com.clean.cleanssakssak.common;

import javax.swing.plaf.PanelUI;

public class Const {// 주로 사용되는 응답값 상수 정의

    public static final int TODO_ROW_COUNT = 8;// todo 페이징 처리
    public static final int DIARY_ROW_COUNT = 10;// diary 페이징 처리

    public static final int DIARY_TITLE_MISSING = -1;// diary 타이틀 없음으로 실패 응답값

    public static final int FAIL = 0;// 실패 시 응답값
    public static final int SUCCESS = 1;// 성공 시 응답값

    public static final int ID_DUPLICATED = -1; // usersignup ID가 중복됨
    public static final int NICKNAME_DUPLICATED = 0; // usersignup 닉네임이 중복됨
    public static final int ID_NICKNAME_NULL = -2; // usersignup ID와 닉네임, 패스워드 데이터가 공란
    public static final int PASSWORD_NULL = -3; // usersignup upw가 공란이거나 데이터가 없음
    public static final int ID_PW_BLANK = -4; // usersignup ID, PW 중에 또는 둘 다 문자 사이 공란이 있다
    public static final int ID_FAIL = 2; // usersignin ID가 다르다
    public static final int PW_FAIL = 3; // usersignin PW가 다르다

    public static final int CHECK_ON = 1; //check시 is_checked에 들어갈 값
    public static final int CHECK_OFF = 0; //check 삭제 시 is_checked에 들어갈 값
    public static final int CANCEL = 2; // check 취소 성공 값
    public static final int IS_DEL = 1;// todo 삭제 시 쓰일 값
}
