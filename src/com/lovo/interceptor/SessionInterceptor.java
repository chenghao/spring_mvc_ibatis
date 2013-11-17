package com.lovo.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lovo.constant.Constant;
import com.lovo.pojo.User;
import com.lovo.service.IUserService;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private List<String> ignoreStrs;
	private List<String> uris;

	@Autowired
	private IUserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);

		String uri = request.getRequestURI();
		String paramPath = request.getQueryString();

		boolean boo = true;
		for (String ignoreStr : ignoreStrs) {
			if (uri.contains(ignoreStr)) {
				boo = false;
				break;
			}
		}
		
		if (boo) {
			if (user == null) {
				Cookie[] cookies = request.getCookies();
				String cookieName = getValue(cookies, Constant.COOKIE_NAME);
				String cookieId = getValue(cookies, Constant.COOKIE_ID);

				if (!"".equals(cookieName) && !"".equals(cookieId)) {
					User u = userService.queryUserAutoLogin(cookieName, cookieId);
					if (u != null) {
						request.getSession().setAttribute(Constant.USER_SESSION, u);

						for (String str : uris) {
							if (str.equals(uri)) {
								response.sendRedirect("user.htm");
								break;
							}
						}
						return true;
					} else {
						response.sendRedirect("login.htm?");
						return false;
					}
				} else {
					response.sendRedirect("login.htm");
					return false;
				}
			}
		}

		return true;
	}

	private String getValue(Cookie[] cookies, String key) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}

	public void setIgnoreStrs(List<String> ignoreStrs) {
		this.ignoreStrs = ignoreStrs;
	}

	public void setUris(List<String> uris) {
		this.uris = uris;
	}

}
