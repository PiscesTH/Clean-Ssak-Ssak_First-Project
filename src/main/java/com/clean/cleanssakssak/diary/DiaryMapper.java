package com.clean.cleanssakssak.diary;

import com.clean.cleanssakssak.diary.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    int insDiary(DiaryInsDto dto); //다이어리 작성

    int insDiaryPic(DiaryInsDto dto); // 다이어리 사진

    int delDiary(DiaryDelDto dto); //다이어리 삭제

    int delDiaryPics(DiaryDelDto dto); // 다이어리 사진 삭제

    int updDiary(DiaryUpdDto dto); //다이어리 수정

    List<DiarySelVo> selDiaryAll(DiarySelDto dto); // 다이어리 전체 조회
    List<String> selDiaryPicAll (int diaryId); //다이어리 전체 사진 조회

    int delDiaryPicForUnregister(int loginedUserId);// 유저 탈퇴 시 해당 유저의 사진 전체 삭제
    int delDiaryForUnregister(int loginedUserId);// 유저 탈퇴 시 해당 유저의 다이어리 전체 삭제
}

