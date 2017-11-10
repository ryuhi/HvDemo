package com.ryuhi.demo.HvDemo.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.ryuhi.demo.HvDemo.validator.annotation.base.CnMobileAnnotation;

public class CnMobileValidator implements ConstraintValidator<CnMobileAnnotation, String> {

	public void initialize(CnMobileAnnotation mobile) {  

	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile("1[34578]\\d{9}$");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

}
