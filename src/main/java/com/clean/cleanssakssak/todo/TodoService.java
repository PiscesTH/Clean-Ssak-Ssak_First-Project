package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.todo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper mapper;

    //청소 todo 하나 등록
    public ResVo postTodo(TodoInsDto dto){

        if (dto.getCleaning() == null || dto.getCleaning().isBlank()){
            return new ResVo(Const.FAIL);
        }
        if (dto.getDoDay() == null || dto.getDoDay().isBlank()){
            return new ResVo(Const.FAIL);
        }

        String[] tmp = dto.getDoDay().split("/");
        List<String> list = new ArrayList<>(Arrays.asList(tmp));
        list.add(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        String date = String.join("-", list);
        dto.setDoDay(date);

        int insResult = mapper.insTodo(dto);

        return new ResVo(dto.getTodoId());

    }

    //등록한 todo 전체 조회(한 페이지에 8개씩 페이징 처리)
    public List<TodoSelAllVo> getTodoAll(TodoSelAllDto dto) {

        dto.setRowCount(Const.TODO_ROW_COUNT);
        dto.setStartIdx((dto.getPage()-1) * Const.TODO_ROW_COUNT);

        return mapper.selTodoAll(dto);

    }

    // todo 내용 수정
    public ResVo patchTodo(TodoUpdDto dto){

        String cleaning = dto.getCleaning();
        if(cleaning == null || cleaning.isBlank()) {//빈문자열 체크
            return new ResVo(Const.FAIL);
        }

        String[] dayArr= dto.getDoDay().split("/");
        List<String> dayList = new ArrayList<>(Arrays.asList(dayArr));

        dayList.add(0, dayList.get(dayList.size()-1));
        dayList.remove(dayList.size()-1);

        String day = String.join("-",dayList);

        dto.setDoDay(day);
        int result = mapper.upTodo(dto);

        return new ResVo(result);
    }

    // todo 삭제
    public ResVo delTodo(TodoDelDto dto){

        int result = mapper.delTodo(dto);
        return new ResVo(result);

    }

    // todo check 처리
    public ResVo toggleCheck(TodoToggleCheckDto dto){
        int result = mapper.selCheck(dto);

        if(result == 1){// 이미 check가 되어있는경우
            dto.setCheck(Const.CHECK_OFF);
            mapper.upCheck(dto);//체크 취소 update
            return new ResVo(Const.CANCEL);
        }else if (result == 0){// check가 없는 경우
            dto.setCheck(Const.CHECK_ON);// 체크 update
            mapper.upCheck(dto);
            return new ResVo(Const.SUCCESS);
        }

        return new ResVo(Const.FAIL);//select 실패 시
    }
}
