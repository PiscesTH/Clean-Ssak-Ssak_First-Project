package com.clean.cleanssakssak.common;

public class Const {// 주로 사용되는 응답값 상수 정의

    public static final int TODO_ROW_COUNT = 8;// todo 페이징 처리
    public static final int DIARY_ROW_COUNT = 10;// diary 페이징 처리

    public static final int DIARY_TITLE_MISSING = -1;// diary 타이틀 없음으로 실패 응답값

    public static final int FAIL = 0;// 실패 시 응답값
    public static final int SUCCESS = 1;// 성공 시 응답값

    public static final int ID_DUPLICATED = -1; // usersignup ID가 중복됨
    public static final int NOT_ALLOWED_ID = -2; // usersignup ID 공란 또는 null
    public static final int NOT_ALLOWED_NICKNAME = -3; // usersignup 닉네임 공란 또는 null
    public static final int NOT_ALLOWED_PW = -4; // usersignup PW 공란 또는 null
    public static final int LOGIN_FAILED_BY_UID = 2; // usersignin ID가 다르다
    public static final int LOGIN_FAILED_BY_UPW = 3; // usersignin PW가 다르다
    public static final int NICKNAME_DUPLICATED = -5; // usersignup 닉네임이 중복됨

    public static final int CHECK_ON = 1; //check시 is_checked에 들어갈 값
    public static final int CHECK_OFF = 0; //check 삭제 시 is_checked에 들어갈 값
    public static final int CANCEL = 2; // check 취소 성공 값
    public static final int IS_DEL = 1;// todo 삭제 시 쓰일 값

    public static final int NULL = -4; // 받아와야 하는 값이 null이라서 실행 불가

    public static final int NOT_EXIST_CLEANING = -1;
    public static final int NOT_ALLOWED_DO_DAY = -2;
}
