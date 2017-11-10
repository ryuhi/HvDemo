package com.ryuhi.demo.HvDemo.validator.annotation.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ryuhi.demo.HvDemo.validator.AlphaNumericValidator;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=AlphaNumericValidator.class)  
public @interface AlphaNumericAnnotation {

	String message() default"{user.password.noneAlphaNumeric}";  
    
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};
    
}
