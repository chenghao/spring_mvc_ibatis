package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error.htm")
public class ErrorController {

	@RequestMapping()
	public String view(){
		return "error";
	}
}
