/**
 * 
 */
package com.chinasofti.service;

import java.util.List;

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
	public Boolean checkUserName(String userName);
	public Object signin(int userId,int activityId,String signAddress); 

	public Boolean approve(int state, String userId, int approveUserid);
   //检查用户名是否已经存在
	public Boolean  joinActive(int userId,int activeId);	
	public Boolean cancelActive(int jid);
	public List getApproveUser();
}
