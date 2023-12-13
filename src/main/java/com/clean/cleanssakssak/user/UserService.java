package com.clean.cleanssakssak.user;


import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.user.model.UserInsSignupDto;
import com.clean.cleanssakssak.user.model.UserSigninDto;
import com.clean.cleanssakssak.user.model.UserSigninVo;
import com.clean.cleanssakssak.user.model.UserUbdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper mapper;

    // 회원가입 메소드
    public ResVo postSignup(UserInsSignupDto dto){

        dto.setUpw(BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt()));// 비밀번호 암호화
        int idCheck = mapper.selIdComparison(dto.getUid());//ID 중복 체크
        Integer nmCheck = mapper.selUserByNickname(dto.getNickname());

        if(idCheck == 1){//ID 중복으로 회원가입 실패 시 응답값
            return new ResVo(Const.ID_DUPLICATED);
        }

        if(nmCheck != null){// NickName 중복으로 회원가입 실패 시 응답값
            return new ResVo(Const.FAIL);
        }

        int result = mapper.insUserSignup(dto);
        // ID가 중복되지 않으니 요청 값으로 INSERT(회원가입) 진행

        return new ResVo(dto.getUserId());
        //위의 IF문에 해당되지 않는다면 INSERT 성공으로 해당 user_id값 리턴
    }

    // 로그인 인증 메소드
    public UserSigninVo postSignin(UserSigninDto dto){

        UserSigninVo vo = new UserSigninVo();
        String password = mapper.selSigninPw(dto);// 받아온 유저의 uid값을 이용해 해당 upw를 SELECT

        if(password == null){//SELECT 하지 못한 것 = 해당 uid가 없다
            vo.setResult(Const.ID_FAIL);
            return vo;
        }else if(BCrypt.checkpw(dto.getUpw(), password)){
            //upw를 SELECT 했다면 암호화 된 password가 고객이 입력한 dto.getUpw와 같은지 체크
            vo = mapper.selSignin(dto);//true라면 로그인 성공 해당 유저의 정보를 SELECT
            vo.setResult(Const.SUCCESS);
            return vo;
        }

        vo.setResult(Const.PW_FAIL);//위의 IF문에 다 해당되지 않는다면 upw가 틀렸다는 뜻

        return vo;
    }

    //유저 회원정보(비밀번호, 닉네임) 변경 처리
    public ResVo patchProfile(UserUbdDto dto){

        int updResult = 0;

        if (dto.getUpw() != null && !dto.getUpw().isBlank()){   //수정할 비밀번호 데이터가 null이거나 공백만 있는 경우 확인
            String hashedUpw = BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt());    //비밀번호 암호화
            dto.setUpw(hashedUpw);
            updResult += mapper.updUserUpw(dto);    //비밀번호 수정
        }

        Integer nicknameCheck = mapper.selUserByNickname(dto.getNickname());    //닉네임 중복 체크용

        if (nicknameCheck == null && dto.getNickname() != null && !dto.getNickname().isBlank()){    //닉네임 중복이 없고 수정할 닉네임 데이터가 null이거나 공백만 있는 경우 확인
            updResult += mapper.updUserNickname(dto);   //닉네임 수정
        }

        return new ResVo(updResult);    // result = 0 : 수정 실패 / result > 0 : 수정 성공
    }

    //유저 회원탈퇴 처리
    public ResVo delProfile(int loginedUserId){

        int delResult = mapper.delUser(loginedUserId);
        return new ResVo(delResult);

    }
}
