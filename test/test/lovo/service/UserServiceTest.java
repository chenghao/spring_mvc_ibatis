package test.lovo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.lovo.BaseTest;

import com.lovo.pojo.User;
import com.lovo.service.IUserService;
import com.lovo.utils.Crypto;

public class UserServiceTest extends BaseTest {
	@Autowired
	private IUserService userService;
	
	@Test
	public void testLogin(){
		User u = new User();
		u.setLoginName("aaa");
		u.setPassword(Crypto.MD5_SHA("111111"));
		
		User user = userService.login(u);
		
		System.err.println(user.getPid()+" -- "+user.getLoginName()+" -- "+user.getUserName());
	}
}
