package com.shyb.evcharging.user.exception;

/**
 * 중복된 이메일로 가입하려는 경우 던집니다.
 */
public class EmailDuplicateException extends RuntimeException{
    public EmailDuplicateException() {
        super("이미 가입된 이메일 주소입니다.");
    }
}
