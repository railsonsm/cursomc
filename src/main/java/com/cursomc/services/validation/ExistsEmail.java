package com.cursomc.services.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailInsertValidator.class)
@Target({ElementType.FIELD,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsEmail {
	String message() default "Erro de validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}