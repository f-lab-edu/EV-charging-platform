package com.shyb.evcharging.card.dto.validator;

import java.lang.annotation.Annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExpiryMonthValidator implements ConstraintValidator<ExpiryMonthConstraint, String> {

    @Override
    public void initialize(
        ExpiryMonthConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 입력 받는 문자열은 01 ~ 12 까지의 값이어야 한다.

        if (value.length() > 2) {
            return false;
        }

        int month = Integer.parseInt(value);
        if (month >= 1 && month <= 12) {
            return true;
        }
        return false;
    }
}
