package com.clean.cleanssakssak.common;

public class Const {// 주로 사용되는 응답값 상수 정의

    public static final int TODO_ROW_COUNT = 8;// todo 페이징 처리
    public static final int DIARY_ROW_COUNT = 10;// diary 페이징 처리

    public static final int DIARY_TITLE_MISSING = -1;
    public static final int DIARY_PICS_MISSING = -2;


    public static final int FAIL = 0;// 실패 시 응답값
    public static final int SUCCESS = 1;// 성공 시 응답값

    public static final int ID_DUPLICATED = -1; // usersignup ID가 중복됨
    public static final int ID_FAIL = 2; // usersignin ID가 다르다
    public static final int PW_FAIL = 3; // usersignin PW가 다르다

    public static final int CHECK_ON = 1; //check시 is_checked에 들어갈 값
    public static final int CHECK_OFF = 0; //check 삭제 시 is_checked에 들어갈 값
    public static final int CANCEL = 2; // check 취소 성공 값
}
