package com.shyb.evcharging.card.exception;

import com.shyb.evcharging.card.dto.CardErrorResponseDto;
import com.shyb.evcharging.user.dto.ErrorResponseDto;
import com.shyb.evcharging.user.exception.EmailDuplicateException;
import com.shyb.evcharging.user.exception.PasswordMisMatchException;
import com.shyb.evcharging.user.exception.UserNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 사용자와 관련된 예외를 처리하는 핸들러입니다.
 */
@ResponseBody
@ControllerAdvice
public class CardExceptionHandler {

    /**
     * 입력값이 Spring Validation 조건에 맞지 않는 경우, 예외를 던집니다.
     *
     * @param ex MethodArgumentNotValidException 예외
     * @return 메시지가 담긴 응답
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CardErrorResponseDto handleMethodArgumentNotValid(ConstraintViolationException ex) {
        return new CardErrorResponseDto(ex.getMessage());
    }
}
