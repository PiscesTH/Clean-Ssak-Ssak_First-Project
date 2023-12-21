package com.clean.cleanssakssak.diary;

import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.diary.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper mapper;

    // 다이어리 작성
    public ResVo postDiary(DiaryInsDto dto) {
        if (!StringUtils.hasText(dto.getTitle())) { //dto.getTitle() == null || dto.getTitle().isBlank()
            return new ResVo(Const.DIARY_TITLE_MISSING); // 제목 null 값이거나 제목 빈 문자열 일 경우 -1
        }
        int insDiaryResult;
        try {
            insDiaryResult = mapper.insDiary(dto);
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        if (insDiaryResult == 0) {//다이어리 insert 실패 시
            return new ResVo(Const.FAIL);
        }
        if (dto.getPics() == null) {     //받아온 사진 리스트 데이터가 null인지 체크
            return new ResVo(dto.getDiaryId());
        }
        List<String> picsList = new ArrayList<>();
        for (String pic : dto.getPics()) {
            if (StringUtils.hasText(pic)) {   //pic != null && !pic.isBlank() , 받은 사진 데이터가 null or 빈 문자열인지 체크
                picsList.add(pic);             //저장할 사진 데이터 분류
            }
        }
        dto.setPics(picsList);  //분류한 사진 데이터 세팅
        if (!picsList.isEmpty()) {  //등록할 사진 있는지 체크
            int insPicsResult;
            try {
                insPicsResult = mapper.insDiaryPic(dto);
            } catch (Exception e) {
                return null;
            }
        }
        return new ResVo(dto.getDiaryId());
    }

    // 다이어리 전체 조회 (10개씩 페이징 처리)
    public List<DiarySelVo> getDiary(DiarySelDto dto) {
        List<DiarySelVo> list;
        try {
            list = mapper.selDiaryAll(dto);
        } catch (Exception e) {
            return null;
        }
        try {
            for (DiarySelVo vo : list) {
                List<String> pics = mapper.selDiaryPicAll(vo.getDiaryId()); //diary id 이용해서
                vo.setPics(pics); //사진정보 저장
            }
        }catch (Exception e) {
            return null;
        }
        return list;
    }

    // 다이어리 수정
    public ResVo patchDiary(DiaryUpdDto dto) {
        if (!StringUtils.hasText(dto.getTitle())) { //dto.getTitle() == null || dto.getTitle().isBlank()
            return new ResVo(Const.DIARY_TITLE_MISSING); // 제목 null 값이거나 제목 빈 문자열 일 경우 -1
        }
        int updDiaryResult;
        try {
            updDiaryResult = mapper.updDiary(dto);
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        int delPicsResult;
        try {
            delPicsResult = mapper.delDiaryPics(DiaryDelDto.builder()   //사진 삭제 처리
                    .diaryId(dto.getDiaryId())
                    .loginedUserId(dto.getLoginedUserId())
                    .build());
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        if (dto.getPics() == null) {      //받아온 사진 리스트 데이터가 null인지 체크
            return new ResVo(Const.SUCCESS);
        }
        List<String> picsList = new ArrayList<>();

        for (String pic : dto.getPics()) {
            if (StringUtils.hasText(pic)) {//pic != null && !pic.isBlank() , 받은 사진 데이터가 null or 빈 문자열인지 체크
                picsList.add(pic);            //저장할 사진 데이터 분류
            }
        }
        dto.setPics(picsList);  //분류된 사진 데이터 세팅
        if (!picsList.isEmpty()) {//등록할 사진 있는지 체크
            int insPicsResult;
            try {
                insPicsResult = mapper.insDiaryPic(DiaryInsDto.builder()    //사진 등록
                        .diaryId(dto.getDiaryId())
                        .pics(dto.getPics())
                        .build());
            } catch (Exception e) {
                return new ResVo(Const.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    // 다이어리 삭제
    public ResVo delDiary(DiaryDelDto dto) {
        int delDiaryResult; // 다이어리 삭제 표시
        try {
            delDiaryResult = mapper.delDiary(dto);
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(delDiaryResult); // 0,1 표시
    }
}
