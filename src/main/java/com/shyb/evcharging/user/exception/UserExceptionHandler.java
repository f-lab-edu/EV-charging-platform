package com.shyb.evcharging.user.exception;

import com.shyb.evcharging.user.dto.ErrorResponseDto;
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
public class UserExceptionHandler {

    /**
     * 사용자 등록 시 패스워드 입력이 동일하지 않은 경우, 예외를 던집니다.
     *
     * @return 메시지가 담긴 응답
     */
    @ExceptionHandler(PasswordMisMatchException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handlePasswordMismatchException() {
        return new ErrorResponseDto("패스워드와 패스워드 확인에 입력된 패스워드가 일치하지 않습니다.");
    }

    /**
     * 이미 가입된 이메일로 등록을 요청할 경우, 예외를 던집니다.
     *
     * @return 메시지가 담긴 응답
     */
    @ExceptionHandler(EmailDuplicateException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleEmailDuplicateException() {
        return new ErrorResponseDto("이미 가입된 이메일입니다.");
    }

    /**
     * 입력값이 Spring Validation 조건에 맞지 않는 경우, 예외를 던집니다.
     *
     * @param ex MethodArgumentNotValidException 예외
     * @return 메시지가 담긴 응답
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        return new ErrorResponseDto(errors.get(0));
    }

    /**
     * 입력값이 Spring Validation 조건에 맞지 않는 경우, 예외를 던집니다.
     *
     * @param ex MethodArgumentNotValidException 예외
     * @return 메시지가 담긴 응답
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMethodArgumentNotValid(ConstraintViolationException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }


    /**
     * 등록되지 않은 사용자를 조회하는 경우, 예외를 던집니다.
     *
     * @return 메시지가 담긴 응답
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleUserNotFoundException() {
        return new ErrorResponseDto("등록되지 않은 사용자입니다.");
    }
}
