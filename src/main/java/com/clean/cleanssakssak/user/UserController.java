package com.clean.cleanssakssak.user;


import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.user.model.UserInsSignupDto;
import com.clean.cleanssakssak.user.model.UserSigninDto;
import com.clean.cleanssakssak.user.model.UserSigninVo;
import com.clean.cleanssakssak.user.model.UserUbdDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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

    @Operation(summary = "회원가입", description = "유저 회원가입 시 입력할 정보\n" +
            "-1 : ID가 중복된다(실패)\n" +
            "0 : nick_name이 중복된다(실패)\n" +
            "user_id( PK값 ) : 회원가입 성공")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호"),
            @Parameter(name = "nickName", description = "별명 / 성함")
    })
    @PostMapping("/signup")
    public ResVo postSignup(@RequestBody UserInsSignupDto dto){
        return service.postSignup(dto);
    }

    //----------------------------------------------------------------------------------------------
    @Operation(summary = "로그인 인증", description = "아이디/ 비밀번호를 활용한 인증처리\n" +
            "1: 성공, 2: 아이디 다름, 3: 비밀번호 다름")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호")
    })
    @PostMapping("/signin")
    public UserSigninVo postSignin(@RequestBody UserSigninDto dto){
        return service.postSignin(dto);
    }

    //----------------------------------------------------------------------------------------------
    @Operation(summary = "유저의 비밀번호/닉네임 변경 처리", description = """
            result = 0 : 변경 실패<br>
            result > 0 : 변경 성공
            """)
    @PatchMapping("/profile")
    public ResVo patchProfile(@RequestBody UserUbdDto dto){
        return service.patchProfile(dto);
    }

    //----------------------------------------------------------------------------------------------
    @Operation(summary = "회원 탈퇴 처리", description = """
            result = 1 : 회원 탈퇴 성공<br>
            result = 0 : 회원 탈퇴 실패
            """)
    @DeleteMapping("/profile")
    public ResVo delProfile(@RequestParam int loginedUserId){
        return service.delProfile(loginedUserId);
    }
}
