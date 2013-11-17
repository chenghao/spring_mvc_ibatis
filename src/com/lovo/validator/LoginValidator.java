package com.lovo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lovo.pojo.User;

@Component("loginValidator")
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginName", "required.loginName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password");
	}

}
