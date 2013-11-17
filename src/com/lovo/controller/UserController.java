package com.lovo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lovo.constant.Constant;
import com.lovo.pojo.User;
import com.lovo.service.IUserService;
import com.lovo.utils.PageUtil;

@Controller
@RequestMapping("user.htm")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping()
	public String view(String userName, ModelMap modelMap) {
		User user = new User();
		user.setUserName(userName);
		user.setPageNo(PageUtil.getCurrentRow(1));
		user.setPageSize(Constant.PAGE_SIZE);

		List<User> users = userService.queryUser(user);
		int pageTotal = getPageTotal(userName);

		modelMap.addAttribute("users", users);
		modelMap.addAttribute(Constant.PAGE_NO, 1);
		modelMap.addAttribute(Constant.PAGE_TOTAL, pageTotal);
		modelMap.addAttribute("userName", userName);

		return "userList";
	}
	
	@RequestMapping(params = "searchUser")
	public String searchUser(User user, ModelMap modelMap){
		int pageNo = user.getPageNo();
		user.setPageNo(PageUtil.getCurrentRow(pageNo));
		user.setPageSize(Constant.PAGE_SIZE);
		List<User> users = userService.queryUser(user);
		
		int pageTotal = user.getPageTotal();
		pageTotal = getPageTotal(user.getUserName());
		
		modelMap.addAttribute("users", users);
		modelMap.addAttribute(Constant.PAGE_TOTAL, pageTotal);
		modelMap.addAttribute(Constant.PAGE_NO, pageNo);
		modelMap.addAttribute("userName", user.getUserName());
		
		return "userList";
	}
	
	//获取总页数
	private int getPageTotal(String userName){
		int pageTotal = PageUtil.getTotalPage(userService.queryRowTotal(userName));
		return pageTotal;
	}
}
