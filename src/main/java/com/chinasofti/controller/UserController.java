package com.chinasofti.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.model.Result;
import com.chinasofti.model.User;
import com.chinasofti.service.IUserService;

import net.sf.json.JSONObject;



@Controller
@RequestMapping("user")
public class UserController {
	@Resource(name="userService")
	IUserService UserServiceImp;
	private static Logger logger = Logger.getLogger(UserController.class);
	@ResponseBody
	@RequestMapping("logon")
	public Object login(String userName,String pwd)
	{
		logger.debug("Will do logon");
		
		return null;
	}

	@ResponseBody
	@RequestMapping("regist")
	public Object regist(@ModelAttribute User user) throws Exception
	{
		JSONObject jsonObject=JSONObject.fromObject(UserServiceImp.userRegist(user));
		Map<String, Object> data = new HashMap<String, Object>();
		Result result = new Result(1, "golbal.success", data);

		data.put("User",UserServiceImp.userRegist(user));
		return result;
	
	}
}
