package com.clean.cleanssakssak.diary;

import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.diary.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper mapper;

    // 다이어리 작성
    public ResVo postDiary(DiaryInsDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            return new ResVo(Const.DIARY_TITLE_MISSING); // 제목 null 값이거나 제목 빈 문자열 일 경우 -1
        }
        int insDiaryResult = mapper.insDiary(dto);
        if (dto.getPics() == null){
            return new ResVo(dto.getDiaryId());
        }
        List<String> picsList = new ArrayList<>();
        for (String pic : dto.getPics()) {
            if (pic != null && !pic.isBlank()) {
                picsList.add(pic);
            }
        }
        dto.setPics(picsList);
        if (!picsList.isEmpty()) {
            int insPicsResult = mapper.insDiaryPic(dto);
        }
        return new ResVo(dto.getDiaryId());
    }

    // 다이어리 삭제
    public ResVo delDiary(DiaryDelDto dto) {
        int result = mapper.delDiaryPics(dto); // 사진 삭제
        int result2 = mapper.delDiary(dto); // 다이어리 삭제
        return new ResVo(result2); // 0,1 표시
    }

    // 다이어리 수정
    public ResVo patchDiary(DiaryUpdDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            return new ResVo(Const.DIARY_TITLE_MISSING); // 제목 null 값이거나 제목 빈 문자열 일 경우 -1
        }
        int updDiaryResult = mapper.updDiary(dto);
        if (updDiaryResult == 0) {
            return new ResVo(Const.FAIL);   //다이어리 수정 안되면 0 리턴
        }
        int delPicsResult = mapper.delDiaryPics(DiaryDelDto.builder()   //사진 삭제 처리
                .diaryId(dto.getDiaryId())
                .loginedUserId(dto.getLoginedUserId())
                .build());
        List<String> picsList = new ArrayList<>();
        for (String pic : dto.getPics()) {
            if (pic != null && !pic.isBlank()) {    //받은 사진 데이터가 null or 빈 문자열인지 체크
                picsList.add(pic);                  //저장할 사진 데이터 분류
            }
        }
        dto.setPics(picsList);  //분류된 사진 데이터 세팅
        if (!picsList.isEmpty()) {     //등록할 사진 없는지 체크
            int insPicsResult = mapper.insDiaryPic(DiaryInsDto.builder()    //사진 등록
                    .diaryId(dto.getDiaryId())
                    .pics(dto.getPics())
                    .build());
        }
        return new ResVo(Const.SUCCESS);
    }

    // 다이어리 전체 조회 (10개씩 페이징 처리)
    public List<DiarySelVo> getDiary(DiarySelDto dto) {
        List<DiarySelVo> list = mapper.selDiaryAll(dto);
        for (DiarySelVo vo : list) {
            List<String> pics = mapper.selDiaryPicAll(vo.getDiaryId()); //diary id 이용해서
            vo.setPics(pics); //사진정보 저장
        }
        return list;
    }

}
