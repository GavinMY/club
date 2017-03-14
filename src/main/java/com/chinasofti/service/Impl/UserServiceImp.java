/**
 * 
 */
package com.chinasofti.service.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.chinasofti.model.User;
import com.chinasofti.service.IUserService;

/**
 * @author Lex
 *
 */
@Service("userService")
public class UserServiceImp implements IUserService{
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	/* (non-Javadoc)
	 * @see com.chinasofti.service.User.IUserService#userRegist()
	 */
	public Object userRegist(User user) {
	int result=jdbcTemplate.update("insert into user(user_name,password,email) values(?,?,?)",   
		                new Object[]{user.getUserName(),user.getPassword(),user.getEmail()});
			 if (result>0) {
				return user;
			}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.chinasofti.service.User.IUserService#userLogin()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object userLogin(String userName,String password,String email)throws Exception {
		// TODO Auto-generated method stub
		String sql = "";
		
		User user=null;
		if (userName!=""&&userName!=null) {
			 sql="select*from user where userName=? and password=?";
		}else if (email!=""&&email!=null) {
			 sql="select*from user where email=? and password=?";
		}
		if (userName!=""&&userName!=null) {
			user= (User)jdbcTemplate.queryForObject(  
	               sql,   
	                new Object[]{userName,password},  
	                new RowMapper(){  
	  
	                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
	                        User user  = new User();  
	                       user.setUserId(rs.getInt("user_Id"));
	                     user.setUserName(rs.getString("user_name"));
	                     user.setUserType(rs.getString("user_type"));
	                        return user;  
	                    }} ); 
		}else if (email!=""&&email!=null) {
			user=(User) jdbcTemplate.queryForObject(  
		               sql,   
		                new Object[]{email,password},  
		                new RowMapper(){  
		  
		                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
		                        User user  = new User();  
		                        user.setUserId(rs.getInt("user_Id"));
			                     user.setUserName(rs.getString("user_name"));
			                     user.setUserType(rs.getString("user_type"));
		                        return user;  
		                    }} );
		}
		return user;
		}
}
