package com.clean.cleanssakssak.diary;

import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.diary.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
@Tag(name = "다이어리 API", description = "다이어리 관련 정보 처리")
public class DiaryController {

    private final DiaryService service;

    //------------------------------- 다이어리 작성 -------------------------------
    @Operation(summary = "다이어리 작성",description = """
    작성 완료 : diary pk(값)<br>
    작성 실패 : 0 <br>
    제목 null 값 혹은 빈 문자열 :-1""")
    @Parameters(value = {
            @Parameter(name = "contents", description = "내용")
            ,@Parameter(name = "title", description = "제목")
            ,@Parameter(name = "user_id", description = "유저")
            ,@Parameter(name = "pics", description = "사진")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping
    public ResVo postDiary (@RequestBody DiaryInsDto dto) {
        return service.postDiary(dto);
    }

    //------------------------------- 다이어리 전체 조회 -------------------------------
    @Operation(summary = "다이어리 조회",description = "조회 완료 :result(1), <br>조회 실패 :result(0)")
    @Parameters(value = {
            @Parameter(name = "userid", description = "유저")
            ,@Parameter(name = "page", description = "페이지")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping
    public List<DiarySelVo> getDiary(DiarySelDto dto) {
        return service.getDiary(dto);
    }

    //------------------------------- 다이어리 수정 -------------------------------
    @Operation(summary = "다이어리 수정",description = """
    수정 완료 : 1<br>
    수정 실패 : 0<br>
    제목 null 값 혹은 빈 문자열 : -1""")
    @Parameters(value = {
            @Parameter(name = "contents", description = "내용")
            ,@Parameter(name = "title", description = "제목")
            ,@Parameter(name = "user_id", description = "유저")
            ,@Parameter(name = "diary_id", description = "다이어리 번호")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping
    public ResVo patchDiary (@RequestBody DiaryUpdDto dto) {
        return service.patchDiary(dto);
    }



    //------------------------------- 다이어리 삭제 -------------------------------
    @Operation(summary = "다이어리 삭제",description = "삭제 완료 : 1 <br>삭제 실패 : 0 ")
    @Parameters(value = {
            @Parameter(name = "user_id", description = "유저")
            ,@Parameter(name = "diary_id", description = "다이어리 번호")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping
    public ResVo delDiary (@RequestBody DiaryDelDto dto) {
        return service.delDiary(dto);
    }
}

