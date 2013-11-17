
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String cpath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cpath+"/";

response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server


/* String uri = request.getServletPath();
if(!uri.contains("showRegister") && !uri.contains("login")){
	User user = (User)session.getAttribute(Constant.USER_SESSION);
	if(user == null){
		Cookie[] cookies = request.getCookies();
		
		AutoLoginUtil.autoLogin(request, cookies);
	}
} */

%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>

<script type="text/javascript" src="static/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="static/js/md5.js"></script>