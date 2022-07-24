package com.godcoder.myhome.validator;

import com.godcoder.myhome.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

// @Component를 써줘야 D.I 됨
@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board b = (Board) obj;
        // if 글쓴칸이 비어있으면 경고메세지 출력
        if(StringUtils.isEmpty(b.getContent())){
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }
    }
}
