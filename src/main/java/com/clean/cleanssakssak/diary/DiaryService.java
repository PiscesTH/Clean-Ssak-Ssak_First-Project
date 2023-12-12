package com.clean.cleanssakssak.diary;

import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.diary.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper mapper;

    // 다이어리 작성 , 제목 사진 내용 생성일자 수정일자
    public ResVo DiaryIns(DiaryInsDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            return new ResVo(-1); // 제목 null 값이거나 제목 빈 문자열 일 경우 -1
        }
        int result = mapper.DiaryIns(dto);

        if (result == 1){ // 제대로 작성이 완료 했으면 사진넣는 작업
            List<String> pics = dto.getPics(); //다이어리 작성에 사용할 사진 가져와서

            if (pics != null && !pics.isEmpty()) { //pics 첨부할 사진이 존재하는지 확인,pics 하나 이상 있으면 참
                DiaryInsPicDto picDto = new DiaryInsPicDto(); // 사진정보
                picDto.setDiaryId(dto.getDiary_id()); //식별자 설정
                picDto.setPics(pics); // 사진 설정
                int picInsResult = mapper.DiaryInsPic(picDto); //사진정보 전달
            }
        }
        return new ResVo(dto.getDiary_id()); // 성공시 diary pk값
    }


    // 다이어리 삭제
    public ResVo DiaryDel(DiaryDelDto dto) {
        int result = mapper.DiaryPicDel(dto); // 사진 삭제
        int result2 = mapper.DiaryDel(dto); // 다이어리 삭제
        return new ResVo(result2); // 0,1 표시
    }

    // 다이어리 수정
    public ResVo DiaryUpt(DiaryUptDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            return new ResVo(-1); // 제목 null 값이거나 제목 빈 문자열 일 경우 -1
        }
        int result = mapper.DiaryUpt(dto);
        if (result == 1) { //수정 성공시
            int result2 = mapper.DiaryPicDel(dto); // 사진 삭제
            List<String> pics = dto.getPics(); //사진 가져오기
            if (pics != null && !pics.isEmpty()) { //pics 첨부할 사진이 존재하는지 확인,pics 하나 이상 있으면 참
                DiaryInsPicDto picDto = new DiaryInsPicDto(); // 사진정보 확인
                picDto.setDiaryId(dto.getDiaryId()); //식별자 설정
                picDto.setPics(dto.getPics()); // 사진 설정
                int picInsResult = mapper.DiaryInsPic(picDto); //사진정보 전달

                if (picInsResult == 0) {
                    return new ResVo(0); // 사진 실패 0
                }
            }
        }
        return new ResVo(result); // 성공 1 실패 0
    }


    // 다이어리 페이징 처리
    public List<DiarySelVo> DiaryGetVo(DiarySelDto dto) {
        List<DiarySelVo> list = mapper.DiarySelAll(dto);
        for (DiarySelVo vo : list) {
            List<String> pics = mapper.DiaryPicAll(vo.getDiaryId()); //diary id 이용해서
            vo.setPics(pics); //사진정보 저장
        }
        return list;
    }

}
