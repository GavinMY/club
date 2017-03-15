/**
 * 
 */
package com.chinasofti.service.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.chinasofti.model.User;
import com.chinasofti.service.IUserService;

/**
 * @author Lex
 *
 */
@Service("userService")
public class UserServiceImp implements IUserService {
	private static Logger logger = Logger.getLogger(UserServiceImp.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chinasofti.service.User.IUserService#userRegist()
	 */
	public Object userRegist(User user) {
		String sql = "insert into user(user_name,password,employid,department,user_type,chineseName) values(?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql,
						new String[] { "user_name", "password", "employid", "department", "user_type","chineseName"});
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmployid());
				ps.setString(4, user.getDepartment());
				ps.setInt(5, 0);
				ps.setString(6, user.getChineseName());
				return ps;
			}
		}, keyHolder);
		logger.debug("new user regist success and get the userid:" + keyHolder.getKey().intValue());

		if (result > 0) {
			user.setUserId(keyHolder.getKey().intValue());
			return user;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chinasofti.service.User.IUserService#userLogin()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public User userLogin(String userName, String password) {
		// TODO Auto-generated method stub
		String sql = "";
		User user = null;
		RowMapper<User> rowMapper=ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		sql = "select*from user where user_name=? and password=?";
	List<User> list=this.jdbcTemplate.query(sql, new Object[] {userName, password }, rowMapper);
	if(list.size()>0)
	{
		user=list.get(0);
	}
		return user;
	}

	@Override
	public Boolean approve(int status, int userId,int approveUserid) {
		Boolean result = false;
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		logger.debug("timestamp"+timestamp);
		String sql = "update user set state ="+status+" and  approveuserid='"+approveUserid+"' where user_id="+userId;		
		int flg = this.jdbcTemplate.update(sql);
		if (flg > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public Boolean checkUserName(String userName) {
		Boolean result = false;
		String sql = "select*from user where user_name=?";
		List list = this.jdbcTemplate.queryForList(sql, userName);
		if (null== list ||list.size()==0) {
			result = true;
		}
		return result;
	}
}
