package com.ryuhi.demo.HvDemo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.ryuhi.demo.HvDemo.validator.annotation.base.AlphaNumericAnnotation;

public class AlphaNumericValidator implements ConstraintValidator<AlphaNumericAnnotation, String> {
	
	@Override
	public void initialize(AlphaNumericAnnotation constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isAlphanumeric(value)) {
			return true;
		}
		return false;
	}

}
