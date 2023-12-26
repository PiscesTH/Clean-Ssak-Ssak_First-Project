package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.todo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper mapper;

    //청소 todo 하나 등록
    public ResVo postTodo(TodoInsDto dto) {

        /*if (dto.getCleaning() == null || dto.getCleaning().isBlank()
        ||dto.getDoDay() == null || dto.getDoDay().isBlank()){  //cleaning, do_day 데이터가 null 이거나 공백만 있는 경우 체크
            return new ResVo(Const.NULL);
        }*/
        if (!StringUtils.hasText(dto.getCleaning())) {  //cleaning, do_day 데이터가 null 이거나 공백만 있는 경우 체크
            return new ResVo(Const.NOT_EXIST_CLEANING);//-1
        }
        if (!StringUtils.hasText(dto.getDoDay())) {
            return new ResVo(Const.NOT_ALLOWED_DO_DAY);//-2
        }

        try {
            int insResult = mapper.insTodo(dto);
            return new ResVo(dto.getTodoId());  //등록한 todo pk값 리턴
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }
        /*if(insResult == 0){
            return new ResVo(Const.FAIL);
        }*/
    }

    //등록한 todo 전체 조회(한 페이지에 8개씩 페이징 처리)
    public List<TodoSelAllVo> getTodoAll(TodoSelAllDto dto) {

        dto.setRowCount(Const.TODO_ROW_COUNT);  //페이징 처리에 필요한 데이터 설정
        dto.setStartIdx((dto.getPage() - 1) * Const.TODO_ROW_COUNT);  //페이징 처리에 필요한 데이터 설정
        try {
            return mapper.selTodoAll(dto);
        } catch (Exception e) {
            return null;
        }
    }

    // todo 내용 수정
    public ResVo patchTodo(TodoUpdDto dto) {

        if (!StringUtils.hasText(dto.getCleaning())) {//빈문자열 체크       if(cleaning == null || cleaning.isBlank())
            return new ResVo(Const.NOT_EXIST_CLEANING);
        }

        if (!StringUtils.hasText(dto.getDoDay())) {// null 체크  //if        if(dto.getDoDay() == null )
            return new ResVo(Const.NOT_ALLOWED_DO_DAY);
        }

        try {
            int updResult = mapper.updTodo(dto);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }
    }

    //todo 내용 수정 시 불러오기

    public TodoSelOneVo getTodoForEdit(TodoSelOneDto dto){
        try {
            return mapper.selTodoOne(dto);
        } catch (Exception e){
            return null;
        }
    }

    // todo 삭제
    public ResVo delTodo(TodoDelDto dto) {
        try {
            int result = mapper.delTodo(dto);
            return new ResVo(result);
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

    }

    // todo check 처리
    public ResVo toggleCheck(TodoToggleCheckDto dto) {
        try {
            Integer result = mapper.selCheck(dto);
            if (result == 0) {// check가 없는 경우      }else if (result == 0){
                dto.setCheck(Const.CHECK_ON);// 체크 update
                mapper.upCheck(dto);
                return new ResVo(Const.SUCCESS);
            }
            if (result == 1) {// 이미 check가 되어있는경우
                dto.setCheck(Const.CHECK_OFF);
                mapper.upCheck(dto);//체크 취소 update
                return new ResVo(Const.CANCEL);
            }
        } catch (Exception e) {
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(Const.FAIL);
    }
}
