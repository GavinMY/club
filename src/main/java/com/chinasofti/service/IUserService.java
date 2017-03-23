/**
 * 
 */
package com.chinasofti.service;

import java.util.List;
import java.util.Map;

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
	

	public Boolean approve(int state, String userId, int approveUserid);
   //检查用户名是否已经存在
	public Boolean  joinActive(int userId,int activeId);	
	public Boolean cancelActive(int jid);
	public List getApproveUser();
	//获取签到人列表
	public Object signlist(String userId,int activeId)throws Exception;
	//管理员进行审核
	public Object signAudit(int userId,String idArray)throws Exception;
	public Object signin(int userId,int activityId); 
	//报名未签到
	public Object CountUnsign(int userId,int activeId)throws Exception;
	//获取所有报过名的User
	public Object joinUserList(int userId)throws Exception;
	 public List<Map<String, Object>> getJoinActiveAndNotsign();
	 public List<Map<String, Object>> pendingaprovesign();
	 public int getJoinActiveAndNotsignCount(String userId);
}
