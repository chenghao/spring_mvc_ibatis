package com.lovo.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lovo.constant.Constant;
import com.lovo.exception.ServiceException;
import com.lovo.pojo.User;
import com.lovo.service.IUserService;

@Controller
@RequestMapping("login.htm")
public class LoginController {
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;

	@Resource(name = "userValidator")
	Validator userValidator;
	@Resource(name = "loginValidator")
	Validator loginValidator;
	
	@RequestMapping()
	public String view() {
		return "login";
	}

	@RequestMapping(params = "showRegister")
	public String showRegister() {
		return "showRegister";
	}

	@RequestMapping(params = "login", method = RequestMethod.POST)
	public String login(@Validated User user, BindingResult result, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		//验证form表单
		loginValidator.validate(user, result);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute(Constant.ERROR_MESSAGE, "用户名或密码不能为空!");
			return "redirect:login.htm";
		}
		
		String autoLogin = request.getParameter("autoLogin");
		
		User u = userService.login(user);
		if (u != null) {
			//如果选中自动登录
			if("autoLoginFlag".equals(autoLogin)){
				Cookie cookieName, cookieId;
				int timeToLive = 60 * 60 * 24 * 7; //7天
				
				cookieName = new Cookie(Constant.COOKIE_NAME, u.getLoginName());
				cookieName.setMaxAge(timeToLive);//设置Cookie过期时间（7天）
				
				String sessionId = request.getSession().getId();
				cookieId = new Cookie(Constant.COOKIE_ID, sessionId);
				cookieId.setMaxAge(timeToLive);
				
				response.addCookie(cookieName);
				response.addCookie(cookieId);
				
				try {
					userService.saveSessionId(u.getLoginName(), sessionId);
				} catch (ServiceException e) {
					LOGGER.error("选择自动登录时，保存用户SessionId失败。", e);
				}
			}
			
			request.getSession().setAttribute(Constant.USER_SESSION, u);

			return "redirect:user.htm";
		} else {
			redirectAttributes.addFlashAttribute(Constant.ERROR_MESSAGE, "登录名或密码错误。");
			redirectAttributes.addFlashAttribute("user", user);

			return "redirect:login.htm";
		}
	}

	@RequestMapping(params = "register", method = RequestMethod.POST)
	public String register(@Validated User user, BindingResult result, RedirectAttributes redirectAttributes){
		userValidator.validate(user, result);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute(Constant.ERROR_MESSAGE, "表单参数有误，请检查后重新提交!");
			return "redirect:login.htm?showRegister";
		}
		
		int pid = 0;
		try {
			pid = userService.register(user);
		} catch (ServiceException e) {
			LOGGER.error("用户注册失败。", e);
		}
		if(pid > 0){
			redirectAttributes.addFlashAttribute(Constant.SUCCESS_MESSAGE, "注册成功，请登录.");	
			return "redirect:login.htm";
		}else if(pid == -1){
			redirectAttributes.addFlashAttribute(Constant.ERROR_MESSAGE, "登录名已存在，就重新填写.");	
			redirectAttributes.addFlashAttribute("user", user);	
			return "redirect:login.htm?showRegister";
		}else{
			redirectAttributes.addFlashAttribute(Constant.ERROR_MESSAGE, "注册失败.");	
			redirectAttributes.addFlashAttribute("user", user);	
			return "redirect:login.htm?showRegister";
		}
	}
	
	@RequestMapping(params = "logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
		if(user == null){
			return "redirect:login.htm";
		}
		
		try {
			userService.delSessionId(user.getLoginName());
		} catch (ServiceException e) {
			LOGGER.error("退出时清除用户表的SessionId失败。", e);
		}
		
		//清空Cookie
		Cookie cookieName = new Cookie(Constant.COOKIE_NAME, null);
		cookieName.setMaxAge(0);
		
		Cookie cookieId = new Cookie(Constant.COOKIE_ID, null);
		cookieId.setMaxAge(0);
		
		response.addCookie(cookieName);
		response.addCookie(cookieId);
		
		//清空Session
		request.getSession().removeAttribute(Constant.USER_SESSION);
		request.getSession().invalidate(); 
		return "redirect:login.htm";
	}
}
