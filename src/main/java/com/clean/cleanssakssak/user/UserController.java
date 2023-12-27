package com.clean.cleanssakssak.user;


import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.user.model.UserInsSignupDto;
import com.clean.cleanssakssak.user.model.UserSigninDto;
import com.clean.cleanssakssak.user.model.UserSigninVo;
import com.clean.cleanssakssak.user.model.UserUbdDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@Tag(name = "유저 API", description = "유저 관련 정보 처리")
public class UserController {
    private final UserService service;

    //---------------------------------- 유저 회원가입 ---------------------------------------
    @Operation(summary = "회원가입", description = """
            유저 회원가입 시 입력할 정보<br>
            -4 : 허용되지 않는 닉네임 형식(실패)<br>
            -3 : 허용되지 않는 비밀번호 형식(실패)<br>
            -2 : 허용되지 않는 아이디 형식(실패)<br>
            -1 : ID가 중복된다(실패)<br>
            -5 : nick_name이 중복된다(실패)<br>
            user_id( PK값 ) : 회원가입 성공""")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호"),
            @Parameter(name = "nickname", description = "별명")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/signup")
    public ResVo postSignup(@RequestBody UserInsSignupDto dto){
        return service.postSignup(dto);
    }

    //---------------------------------- 유저 로그인 ---------------------------------------
    @Operation(summary = "로그인 인증", description = """
            아이디/ 비밀번호를 활용한 인증처리<br>
            1: 성공<br>
            2: 아이디 다름<br>
            3: 비밀번호 다름<br>
            -4 : 아이디 또는 비밀번호에 null이 들어옴
            """)
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/signin")
    public UserSigninVo postSignin(@RequestBody UserSigninDto dto){
        return service.postSignin(dto);
    }

    //---------------------------------- 유저 정보 수정 ---------------------------------------
    @Operation(summary = "유저의 비밀번호/닉네임 변경 처리", description = """
            result = -6 : 닉네임, 비밀번호 입력 안함<br>
            result = 1 : ( 수정 한 개만 시도 시 )변경 성공<br>
            result = 2 : ( 수정 두 개 시도 시 )변경 성공<br>
            result = -4 : 허용되지 않는 비밀번호 형식<br>
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping("/profile")
    public ResVo patchProfile(@RequestBody UserUbdDto dto){
        return service.patchProfile(dto);
    }

    //---------------------------------- 유저 탈퇴 처리 ---------------------------------------
    @Operation(summary = "회원 탈퇴 처리", description = """
            result = 1 : 회원 탈퇴 성공<br>
            result = 0 : 회원 탈퇴 실패
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping("/profile")
    public ResVo delProfile(@RequestParam int loginedUserId){
        return service.delProfile(loginedUserId);
    }
}
