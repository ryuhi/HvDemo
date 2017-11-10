package com.ryuhi.demo.HvDemo.entity;

import java.io.Serializable;

import javax.validation.GroupSequence;

import com.ryuhi.demo.HvDemo.validator.annotation.element.user.CaptchaAnnotation;
import com.ryuhi.demo.HvDemo.validator.annotation.element.user.MobileUserNameAnnotation;
import com.ryuhi.demo.HvDemo.validator.annotation.element.user.PasswordAnnotation;
import com.ryuhi.demo.HvDemo.validator.annotation.element.user.SmsCaptchaAnnotation;
import com.ryuhi.demo.HvDemo.validator.group.bizArea.User;
import com.ryuhi.demo.HvDemo.validator.group.bizArea.UserLogin;
import com.ryuhi.demo.HvDemo.validator.group.element.Element1;
import com.ryuhi.demo.HvDemo.validator.group.element.Element2;
import com.ryuhi.demo.HvDemo.validator.group.element.Element3;
import com.ryuhi.demo.HvDemo.validator.group.element.Element4;

import lombok.Data;

@Data
@GroupSequence({Element1.class, Element2.class, Element3.class, Element4.class,  UserEntity.class})  
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 108570894682714243L;
	
	private long id;
	/**
	 * 用户名
	 */
	@MobileUserNameAnnotation(groups = {Element1.class,UserLogin.class, User.class})
	private String name;
	/**
	 * 密码
	 */
	@PasswordAnnotation(groups = {Element2.class, UserLogin.class, User.class})
	private String password;
	/**
	 * 验证码
	 */
	@CaptchaAnnotation(groups = {Element3.class, UserLogin.class, User.class})
	private String captcha;
	
	/**
	 * 短信验证码
	 */
	@SmsCaptchaAnnotation(groups = {User.class, Element4.class})
	private String smsCaptcha;
}
