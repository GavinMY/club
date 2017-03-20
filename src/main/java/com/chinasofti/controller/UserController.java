package com.chinasofti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.model.Activity;
import com.chinasofti.model.Result;
import com.chinasofti.model.User;
import com.chinasofti.service.IUserService;
import com.chinasofti.service.Impl.ActiveServiceImpl;

import net.sf.json.JSONArray;

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
	public Object regist(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) throws Exception {
		Result result = null;

		if (bindingResult.hasErrors()) {
			Map<String, Object> data = new HashMap<String, Object>();
			JSONArray errorArray = new JSONArray();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorArray.add(error.getDefaultMessage());
			}
			data.put("errors", errorArray);
			result = new Result(-1, velocityConf.getProperty("golbal.error"), data);
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
	public Object approve(String userId, int approveUserid) {
		Result result = null;
		logger.debug("userId:"+userId);
		if (null == userId ||""==userId) {
			result = new Result(-1, "the userId is null", null);
		} else {
			
			Boolean flg = UserServiceImp.approve(1, userId, approveUserid);
			if (flg) {
				result = new Result(1, velocityConf.getProperty("golbal.success"), null);
			} else {
				result = new Result(-1, "update error", null);
			}
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
	public Object signin(int userId, int activityId, String signAddress) {
		Map<String, Object> data = new HashMap<String, Object>();
		Result result = null;
		Boolean flg = (Boolean) UserServiceImp.signin(userId, activityId, signAddress);
		if (flg) {

			result = new Result(1, "sign in successful", null);
		} else {
			result = new Result(-1, "sign in failed", null);
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
			boolean hasjoin = activeServiceImpl.ifHasJoinActive(activeId, userId);// if
																					// has
																					// been
																					// join
																					// the
																					// active
			if (!hasjoin) {
				Boolean flg = UserServiceImp.joinActive(userId, activeId);
				if (flg) {
					result = new Result(1, "join active success", null);
				} else {
					result = new Result(-1, velocityConf.getProperty("golbal.error"), null);
				}
			} else {
				result = new Result(-1, "you has been join this active", null);
			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("canceActive")
	public Object canceActive(int jid) {
		Result result = null;
		boolean isjoin = activeServiceImpl.ifHasJoinActive(jid);
		if (!isjoin) {
			result = new Result(-1, "you has not join this active", null);
		} else {
			Boolean flg = UserServiceImp.cancelActive(jid);
			if (flg) {
				result = new Result(1, "success", null);
			} else {
				result = new Result(-1, velocityConf.getProperty("golbal.error"), null);
			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("getApproveList")
	public Object getApproveList() {
		Result result = null;
		List list = UserServiceImp.getApproveUser();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("approveUserList", list);
		result = new Result(1, "success", data);
		return result;
	}
}
