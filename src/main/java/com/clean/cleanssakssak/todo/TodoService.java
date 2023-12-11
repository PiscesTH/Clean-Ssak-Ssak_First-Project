package com.clean.cleanssakssak.todo;

import com.clean.cleanssakssak.common.Const;
import com.clean.cleanssakssak.common.ResVo;
import com.clean.cleanssakssak.todo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper mapper;

    public ResVo patchTodo(TodoUpDto dto){// todo 내용 수정
        int result = mapper.upTodo(dto);
        return new ResVo(result);
    }

    public ResVo delTodo(TodoDelDto dto){// todo 삭제
        int result = mapper.delTodo(dto);
        return new ResVo(result);
    }

    public ResVo toggleCheck(TodoToggleCheckDto dto){// todo check 처리
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
