package com.ryuhi.demo.HvDemo.validator.annotation.element.user;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.GroupSequence;
import javax.validation.Payload;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ryuhi.demo.HvDemo.validator.annotation.base.AlphaNumericAnnotation;
import com.ryuhi.demo.HvDemo.validator.group.inner.First;
import com.ryuhi.demo.HvDemo.validator.group.inner.Second;
import com.ryuhi.demo.HvDemo.validator.group.inner.Third;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@GroupSequence({First.class, Second.class, Third.class, CaptchaAnnotation.class})
@NotBlank(message="{user.captcha.empty}", groups = {First.class})
@AlphaNumericAnnotation(message="{user.captcha.noneAlphaNumeric}", groups = {Second.class})
@Length(max=4,min=4, groups = {Third.class})
@Constraint(validatedBy = { })  
public @interface CaptchaAnnotation {

	String message() default "";  
    Class<?>[] groups() default { };  
    Class<? extends Payload>[] payload() default { };
    
    //指定多个时使用  
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})  
    @Retention(RetentionPolicy.RUNTIME)  
    @Documented  
    @interface List {  
    	CaptchaAnnotation[] value();  
    }  
}
