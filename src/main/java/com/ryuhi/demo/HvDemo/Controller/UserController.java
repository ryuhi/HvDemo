package com.ryuhi.demo.HvDemo.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryuhi.demo.HvDemo.entity.RestResult;
import com.ryuhi.demo.HvDemo.entity.UserEntity;
import com.ryuhi.demo.HvDemo.validator.group.bizArea.User;
import com.ryuhi.demo.HvDemo.validator.group.bizArea.UserLogin;

@Controller
public class UserController extends BaseController {
	
	@RequestMapping(value= {"/","/index"}, method=RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> pars = new ConcurrentHashMap<>();
		noBorderLayoutViewer("/user/index.ftl", pars, request, response);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public RestResult login(@Validated(UserLogin.class) UserEntity login,  BindingResult result,HttpServletRequest request, HttpServletResponse response) {
		
	    if (result.hasErrors()) {
	    	String message = processValidateMsg(result);
	    	return RestResult.failureResult(message);
	    }
	    return RestResult.successResult();
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> pars = new ConcurrentHashMap<>();
		noBorderLayoutViewer("/user/register.ftl", pars, request, response);
	}
	
	@RequestMapping(value="/doRegister", method=RequestMethod.POST)
	@ResponseBody
	public RestResult register(@Validated(User.class) UserEntity user,  BindingResult result) {
	    if (result.hasErrors()) {
	    	String message = processValidateMsg(result);
	    	return RestResult.failureResult(message);
	    }
	    return RestResult.successResult();
	}
}
