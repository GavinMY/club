/**
 * 
 */
package com.chinasofti.service;

import com.chinasofti.model.User;

/**
 * @author Lex
 *
 */ 
public interface IUserService {
	/*
	 * 用户注册接口
	 */
	public Object userRegist(User user)throws Exception;
	/*
	 * 用户登录接口
	 */
	public User userLogin(String userName,String password);
	//注册审批
	public Boolean approve(int status,int userId,int approveUserid);
	public Boolean checkUserName(String userName);
	public Object signin(int userId,int activityId,String signAddress); 
}
