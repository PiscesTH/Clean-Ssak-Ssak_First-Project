package com.clean.cleanssakssak.diary;

import com.clean.cleanssakssak.diary.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    int insDiary(DiaryInsDto dto); //다이어리 작성

    int insDiaryPic(DiaryInsPicDto dto); // 다이어리 사진

    int delDiary(DiaryDelDto dto); //다이어리 삭제

    int delDiaryPic(DiaryDelDto dto); // 다이어리 사진 삭제

    int UpdDiary(DiaryUpdDto dto); //다이어리 수정

    int delDiaryPic(DiaryUpdDto dto); // 다이어리 사진 수정

    List<DiarySelVo> selDiaryAll(DiarySelDto dto); // 다이어리 전체 조회
    List<String> selDiaryPicAll (int diaryId); //다이어리 전체 사진 조회

}

