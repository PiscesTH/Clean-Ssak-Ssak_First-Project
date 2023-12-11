package com.clean.cleanssakssak.diary;

import com.clean.cleanssakssak.diary.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    int DiaryIns(DiaryInsDto dto); //다이어리 작성

    int DiaryInsPic(DiaryInsPicDto dto); // 다이어리 사진


    int DiaryPicDel(DiaryDelDto dto); // 다이어리 사진 삭제

    int DiaryDel(DiaryDelDto dto); //다이어리 삭제


    int DiaryUpt(DiaryUptDto dto); //다이어리 수정

    int DiaryPicDel(DiaryUptDto dto);

    ; // 다이어리 사진 수정

    List<DiarySelVo> DiarySelAll(DiarySelDto dto); // 페이징처리

}

