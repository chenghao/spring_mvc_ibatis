package com.lovo.utils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lovo.constant.Constant;
import com.lovo.pojo.User;
import com.lovo.service.IUserService;

@Component
public class AutoLoginUtil {

	@Autowired
	private IUserService userService;

	private static AutoLoginUtil autoLoginUtil;

	@PostConstruct
	public void init() {
		autoLoginUtil = this;
		autoLoginUtil.userService = this.userService;
	}

	public static void autoLogin(HttpServletRequest request, Cookie[] cookies) {
		String cookieName = getValue(cookies, Constant.COOKIE_NAME);
		String cookieId = getValue(cookies, Constant.COOKIE_ID);

		if (!"".equals(cookieName) && !"".equals(cookieId)) {
			User u = autoLoginUtil.userService.queryUserAutoLogin(cookieName, cookieId);
			if (u != null) {
				request.getSession().setAttribute(Constant.USER_SESSION, u);
			}
		}
	}

	private static String getValue(Cookie[] cookies, String key) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}

}
