package com.jiahuiling.framework.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = {PhoneNumberValidator.class})
public @interface PhoneNumber {

    String message() default "手机号格式不正确，需为11位数字";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
