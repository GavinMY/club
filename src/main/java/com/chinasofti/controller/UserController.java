package com.chinasofti.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@ResponseBody
	@RequestMapping("logon")
	public Object login(String userName,String pwd)
	{
		logger.debug("Will do logon");
		
		return null;
	}
}
