package com.chinasofti.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.model.Result;
import com.chinasofti.model.User;
import com.chinasofti.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {	
	private static Logger logger = Logger.getLogger(UserController.class);
	@Resource(name = "userService")
	IUserService UserServiceImp;
	@Autowired
    public Properties velocityConf;
	@ResponseBody
	@RequestMapping("logon")
	public Object login(String userName, String password) {
		Result result = null;
		User users = UserServiceImp.userLogin(userName, password);
		if (null == users) {
			result = new Result(-1, velocityConf.getProperty("user.invalid"), null);
		} else {
			if (users.getState() == 0) {
				result = new Result(-1, velocityConf.getProperty("user.pending"), null);
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("userInfo", users);
				result = new Result(1, velocityConf.getProperty("golbal.success"), data);

			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("regist")
	public Object regist(@ModelAttribute("user") User user) throws Exception {
		Result result = null;
		if (null == user && null == user.getUserName()) {//用户名不能为空
			result = new Result(-1, velocityConf.getProperty("user.emptyname"), null);
		} else {
			Boolean flg = UserServiceImp.checkUserName(user.getUserName());
			if (flg) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("User", UserServiceImp.userRegist(user));
				result = new Result(1,  velocityConf.getProperty("golbal.success"), data);
			} else {
				result = new Result(-1, velocityConf.getProperty("user.inused"), null);
			}
		}
		return result;
	}

	/* 审批用户 */
	@ResponseBody
	@RequestMapping("approve")
	public Object approve(int userId,int approveUserid) {
		Result result = null;
		Boolean flg = UserServiceImp.approve(1, userId,approveUserid);
		if (flg) {
			result = new Result(1,  velocityConf.getProperty("golbal.success"), null);
		} else {
			result = new Result(-1, "update error", null);
		}

		return result;
	}

	@ResponseBody
	@RequestMapping("checkUserName")
	public Object checkUserName(String userName) {
		Boolean flg = UserServiceImp.checkUserName(userName);
		Result result = null;
		if (flg) {

			result = new Result(1, "can regist", null);
		} else {
			result = new Result(-1, velocityConf.getProperty("user.inused"), null);
		}
		return result;
	}

}
