package com.ryuhi.demo.HvDemo.validator.annotation.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ryuhi.demo.HvDemo.validator.CnMobileValidator;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=CnMobileValidator.class)  
public @interface CnMobileAnnotation {

	String message() default"{user.mobile.noneMobile}";  
    
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};
    
    //指定多个时使用  
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})  
    @Retention(RetentionPolicy.RUNTIME)  
    @Documented  
    @interface List {  
    	CnMobileAnnotation[] value();  
    }  
}
