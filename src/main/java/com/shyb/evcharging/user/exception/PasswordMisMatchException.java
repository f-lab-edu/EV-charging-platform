package com.shyb.evcharging.user.exception;

/**
 * 패스워드와 패스워드 확인에 입력된 값이 일치하지 않는 경우 던집니다.
 */
public class PasswordMisMatchException extends RuntimeException{

    public PasswordMisMatchException() {
        super("패스워드와 패스워드 확인에 입력된 패스워드가 일치하지 않습니다.");
    }
}
