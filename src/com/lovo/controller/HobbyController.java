package com.lovo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lovo.constant.Constant;
import com.lovo.exception.ServiceException;
import com.lovo.pojo.Hobby;
import com.lovo.pojo.User;
import com.lovo.service.IHobbyService;
import com.lovo.utils.PageUtil;

@Controller
@RequestMapping("hobby.htm")
public class HobbyController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(HobbyController.class);
	
	@Autowired
	private IHobbyService hobbyService;

	@RequestMapping()
	public String view(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
		Integer pid = user.getPid();
		
		List<Hobby> hobbys = hobbyService.queryAllHobby(pid, 1, Constant.PAGE_SIZE);
		int pageTotal = getPageTotal();
		
		modelMap.addAttribute("hobbys", hobbys);
		modelMap.addAttribute(Constant.PAGE_NO, 1);
		modelMap.addAttribute(Constant.PAGE_TOTAL, pageTotal);
		
		return "hobby";
	}

	@RequestMapping(params = "moreHobby")
	public String moreHobby(Hobby hobby, ModelMap modelMap, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
		Integer pid = user.getPid();
		
		List<Hobby> hobbys = hobbyService.queryAllHobby(pid, hobby.getPageNo(), Constant.PAGE_SIZE);
		
		modelMap.addAttribute("hobbys", hobbys);
		modelMap.addAttribute(Constant.PAGE_NO, hobby.getPageNo());
		modelMap.addAttribute(Constant.PAGE_TOTAL, hobby.getPageTotal());
		
		return "hobby";
	}
	
	@RequestMapping(params = "deleteHobby", method = RequestMethod.POST)
	public void deleteHobby(int pid, HttpServletResponse response){
		try {
			hobbyService.deleteHobby(pid);
			senderText(response, SUCCESS);
		} catch (ServiceException e) {
			LOGGER.error("根据pid为" + pid + "删除hobby表的数据失败。", e);
			senderText(response, ERROR);
		}
	}
	
	//获取总页数
	private int getPageTotal() {
		int pageTotal = PageUtil.getTotalPage(hobbyService.queryRowTotal());
		return pageTotal;
	}
}
