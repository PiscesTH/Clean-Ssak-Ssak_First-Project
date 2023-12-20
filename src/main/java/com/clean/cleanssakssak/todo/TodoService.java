package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.todo.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (StringUtils.isBlank(dto.getCleaning())) {  //cleaning, do_day 데이터가 null 이거나 공백만 있는 경우 체크
            return new ResVo(Const.NULL);
        }
        if (StringUtils.isBlank(dto.getDoDay())) {
            return new ResVo(Const.NULL);
        }

        String[] tmp = dto.getDoDay().split("/");   //입력받은 날짜 데이터 원하는 형식으로 변경
        List<String> list = new ArrayList<>(Arrays.asList(tmp));
        list.add(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        String date = String.join("-", list);
        dto.setDoDay(date);     //원하는 데이터 형식으로 변경한 날짜 세팅

        try {
            int insResult = mapper.insTodo(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if(insResult == 0){
            return new ResVo(Const.FAIL);
        }*/

        return new ResVo(dto.getTodoId());  //등록한 todo pk값 리턴

    }

    //등록한 todo 전체 조회(한 페이지에 8개씩 페이징 처리)
    public List<TodoSelAllVo> getTodoAll(TodoSelAllDto dto) {

        dto.setRowCount(Const.TODO_ROW_COUNT);  //페이징 처리에 필요한 데이터 설정
        dto.setStartIdx((dto.getPage() - 1) * Const.TODO_ROW_COUNT);  //페이징 처리에 필요한 데이터 설정

        return mapper.selTodoAll(dto);

    }

    // todo 내용 수정
    public ResVo patchTodo(TodoUpdDto dto) {

        if (StringUtils.isBlank(dto.getDoDay())) {// null 체크  //if        if(dto.getDoDay() == null )
            return new ResVo(Const.NULL);
        }

        if (StringUtils.isBlank(dto.getCleaning())) {//빈문자열 체크       if(cleaning == null || cleaning.isBlank())
            return new ResVo(Const.NULL);
        }

        String[] dayArr = dto.getDoDay().split("/");//입력받은 날짜 데이터 원하는 형식으로 변경
        List<String> dayList = new ArrayList<>(Arrays.asList(dayArr));
        dayList.add(0, dayList.get(dayList.size() - 1));
        dayList.remove(dayList.size() - 1);
        String day = String.join("-", dayList);
        dto.setDoDay(day);      //원하는 데이터 형식으로 변경한 날짜 세팅

        try {
            int result = mapper.updTodo(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(Const.SUCCESS);
    }

    // todo 삭제
    public ResVo delTodo(TodoDelDto dto) {

        int result = mapper.delTodo(dto);
        return new ResVo(result);

    }

    // todo check 처리
    public ResVo toggleCheck(TodoToggleCheckDto dto) {
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
        return new ResVo(Const.FAIL);//select 실패 시
    }
}
