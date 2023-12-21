package com.clean.cleanssakssak.user;


import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.diary.DiaryMapper;
import com.clean.cleanssakssak.todo.TodoMapper;
import com.clean.cleanssakssak.user.model.UserInsSignupDto;
import com.clean.cleanssakssak.user.model.UserSigninDto;
import com.clean.cleanssakssak.user.model.UserSigninVo;
import com.clean.cleanssakssak.user.model.UserUbdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final TodoMapper todoMappermapper;
    private final DiaryMapper diaryMappermapper;


    //----------------------------------------------회원가입 메소드-----------------------------------------------
    public ResVo postSignup(UserInsSignupDto dto){

/*
       if(dto.getUid() == null || dto.getNickname() == null ||
                dto.getUid().isBlank() || dto.getNickname().isBlank() ||
               dto.getUpw() == null || dto.getUpw().isBlank()){//ID와 NickName, password 공란 또는 null만 들어옴
            return new ResVo(Const.ID_NICKNAME_PW_NULL);
        }else if(dto.getUid().contains(" ") || dto.getUpw().contains(" ")
                ){//ID와 PW 사이에 공백이 포함됨 들어옴
            return new ResVo(Const.ID_PW_BLANK);
        }
*/
        if(!StringUtils.hasText(dto.getUid()) || StringUtils.containsWhitespace(dto.getUid())){//ID와 NickName, password 공란 또는 null만 들어옴
            return new ResVo(Const.NOT_ALLOWED_ID);//-2
        }
        if(!StringUtils.hasText(dto.getUpw()) || StringUtils.containsWhitespace(dto.getUpw())){
            return new ResVo(Const.NOT_ALLOWED_PW);//-3
        }
        if(!StringUtils.hasText(dto.getNickname())){
            return new ResVo(Const.NOT_ALLOWED_NICKNAME);//-4
        }

        int idCheck;
        Integer nmCheck;

        try {

            idCheck = userMapper.selIdComparison(dto.getUid());//ID 중복 체크
            nmCheck = userMapper.selUserByNickname(dto.getNickname());// 닉네임 중복 체크

        }catch (Exception e){
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        if(idCheck == 1){//ID 중복으로 회원가입 실패 시 응답값
            return new ResVo(Const.ID_DUPLICATED);
        }

        if(nmCheck != null){// NickName 중복으로 회원가입 실패 시 응답값
            return new ResVo(Const.NICKNAME_DUPLICATED);
        }

        dto.setUpw(BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt()));// 비밀번호 암호화

        try {
            int result = userMapper.insUserSignup(dto);
            // ID와 닉네임이 중복되지 않으니 요청 값으로 INSERT(회원가입) 진행
        }catch (Exception e){
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        return new ResVo(dto.getUserId());
        //위의 IF문에 해당되지 않는다면 INSERT 성공으로 해당 user_id값 리턴
    }

    //----------------------------------------로그인 인증 메소드----------------------------------------
    public UserSigninVo postSignin(UserSigninDto dto){

        UserSigninVo vo = new UserSigninVo();

        if(dto.getUid() == null || dto.getUpw() == null){
            vo.setResult(Const.NULL);
            return vo;
        }

        try {

            String hashedPassword = userMapper.selSigninPw(dto);// 받아온 유저의 uid값을 이용해 해당 upw를 SELECT

            if(hashedPassword == null){//SELECT 하지 못한 것 = 해당 uid가 없다
                vo.setResult(Const.LOGIN_FAILED_BY_UID);
                return vo;
            }
            if(BCrypt.checkpw(dto.getUpw(), hashedPassword)){
                //upw를 SELECT 했다면 암호화 된 password가 고객이 입력한 dto.getUpw와 같은지 체크
                vo = userMapper.selSigninInfo(dto);//true라면 로그인 성공 해당 유저의 정보를 SELECT
                vo.setResult(Const.SUCCESS);
                return vo;
            }
        }catch (Exception e){
            return null;
        }

        vo.setResult(Const.LOGIN_FAILED_BY_UPW);//위의 IF문에 다 해당되지 않는다면 upw가 틀렸다는 뜻

        return vo;
    }

    //-----------------------------------유저 회원정보(비밀번호, 닉네임) 변경 처리-----------------------------------
    public ResVo patchProfile(UserUbdDto dto){

        if (!StringUtils.hasText(dto.getUpw()) && !StringUtils.hasText(dto.getNickname())){//비밀번호, 닉네임 둘 다 값이 없을 경우
            return new ResVo(Const.NOT_ALLOWED_ID + Const.NOT_ALLOWED_PW);//-6
        }
        if (StringUtils.hasText(dto.getUpw()) && StringUtils.containsWhitespace(dto.getUpw())) {//수정할 비밀번호 데이터에 공백이 포함되어 있다
            return new ResVo(Const.NOT_ALLOWED_PW);//-4
        }

        int updResult = 0;

        try{
        if (StringUtils.hasText(dto.getUpw())){//수정할 비밀번호 데이터가 제대로 들어온 경우
            String hashedUpw = BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt());//비밀번호 암호화
            dto.setUpw(hashedUpw);
            updResult += userMapper.updUserUpw(dto);//비밀번호 수정
        }}catch (Exception e){
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        if(!StringUtils.hasText(dto.getNickname())){
            return new ResVo(updResult);
        }

        try{

        Integer nicknameCheck = userMapper.selUserByNickname(dto.getNickname());//닉네임 중복 체크용

        if (nicknameCheck == null){
            //닉네임 중복이 없고 수정할 닉네임 데이터가 제대로 들어온 경우
            updResult += userMapper.updUserNickname(dto);//닉네임 수정

        }}catch(Exception e){
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        return new ResVo(updResult);
        // result = 0 : 수정 실패 / result = 1 : ( 수정 한 개만 시도 시 ) 수정 성공 /result = 2 : ( 수정 두 개 시도 시 )변경 성공
    }

    //-------------------------------------------유저 회원탈퇴 처리----------------------------------------
    public ResVo delProfile(int loginedUserId){

        int delResult;

        try {

            int delDiaryPicResult = diaryMappermapper.delDiaryPicForUnregister(loginedUserId);//회원탈퇴 할 유저의 다이어리 사진 삭제
            int delDiaryResult = diaryMappermapper.delDiaryForUnregister(loginedUserId);//회원탈퇴 할 유저의 다이어리 삭제
            int delTodoResult = todoMappermapper.delTodoForUnregister(loginedUserId);//회원탈퇴 할 유저의 todo 삭제
            delResult = userMapper.delUser(loginedUserId);//회원탈퇴 할 유저의 정보 삭제

        }catch(Exception e){
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

        return new ResVo(delResult);

    }
}
