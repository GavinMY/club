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

import com.chinasofti.model.Activity;
import com.chinasofti.model.Result;
import com.chinasofti.model.User;
import com.chinasofti.service.IUserService;
import com.chinasofti.service.Impl.ActiveServiceImpl;

@Controller
@RequestMapping("user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Resource(name = "userService")
	IUserService UserServiceImp;
	@Autowired
	public Properties velocityConf;
	@Autowired
	private ActiveServiceImpl activeServiceImpl;

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

		if (null == user && null == user.getUserName()) {
			result = new Result(-1, velocityConf.getProperty("user.emptyname"), null);
		} else {
			Boolean flg = UserServiceImp.checkUserName(user.getUserName());
			if (flg) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("User", UserServiceImp.userRegist(user));
				result = new Result(1, velocityConf.getProperty("golbal.success"), data);
			} else {
				result = new Result(-1, velocityConf.getProperty("user.inused"), null);
			}
		}
		return result;
	}

	/* 审批用户 */
	@ResponseBody
	@RequestMapping("approve")
	public Object approve(int userId, int approveUserid) {
		Result result = null;
		Boolean flg = UserServiceImp.approve(new Integer(1), new Integer(userId), new Integer(approveUserid));
		if (flg) {
			result = new Result(1, velocityConf.getProperty("golbal.success"), null);
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
	@ResponseBody
	@RequestMapping("signin")
	public Object signin(int userId,int activityId,String signAddress) {
		Map<String, Object> data = new HashMap<String, Object>();
		Result result = null;
		Boolean flg = (Boolean) UserServiceImp.signin(userId, activityId, signAddress);
		if (flg) {

			result = new Result(1, "sign in successful", null);
		} else {
			result = new Result(-1,"sign in failed", null);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("joinActive")
	public Object joinActive(int userId, int activeId) {
		Result result = null;
		Activity active = activeServiceImpl.findActiveById(activeId);
		if (null == active) {
			result = new Result(-1, "active not exist", null);
		} else {
			Boolean flg = UserServiceImp.joinActive(userId, activeId);
			if (flg) {
				result = new Result(1, "join active success", null);
			} else {
				result = new Result(-1, velocityConf.getProperty("golbal.error"), null);
			}
		}
		return result;
	}
}
