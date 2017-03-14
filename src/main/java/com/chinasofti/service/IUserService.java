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
	public Object userLogin(String userName,String password,String email)throws Exception;

}
