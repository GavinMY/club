/**
 * 
 */
package com.chinasofti.service.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
	public Object userRegist(final User user) {
		final String registsql = "insert into user(user_name,password,employid,department,user_type,chineseName) values(?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(registsql,
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

	public Boolean approve(int state, String userId, int approveUserid) {
		Boolean result = false;
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		int flg = jdbcTemplate.update("update user set state=?, approveuserid=? , approvetime=?  where user_id in ("+userId+")",
				  new Object[]{state,approveUserid,timestamp});
		if (flg > 0) {
			result = true;
		}
		return result;
	}

	public Boolean checkUserName(String userName) {
		Boolean result = false;
		String sql = "select*from user where user_name=?";
		List list = this.jdbcTemplate.queryForList(sql, userName);
		if (null== list ||list.size()==0) {
			result = true;
		}
		return result;
	}

	

	public Boolean joinActive(int userId, int activeId) {
		Boolean result = false;
		int flg = jdbcTemplate.update("insert into joinuser(userid,activeid) values(?,?)",
				  new Object[]{userId,activeId});
		if (flg > 0) {
			result = true;
		}
		return result;
	}
	public Boolean cancelActive(int jid)
	{
		Boolean result = false;
		String sql="delete from joinuser where id=?";
		int flg = this.jdbcTemplate.update(sql, jid);
		if (flg > 0) {
			result = true;
		}
		return result;
	}

	public List getApproveUser() {
		String sql="select u.user_id,u.user_name,u.employid,u.department,u.chineseName,u.create_time from user u where state=0";
		List list=this.jdbcTemplate.queryForList(sql);
		return list;
	}
	public Object signin(int userId,int activityId) {
	if (jdbcTemplate.queryForInt("select count(*) from joinuser where userid="+userId+" and activeid="+activityId)>0) {
		if(jdbcTemplate.queryForInt("select count(*) from sign where userid="+userId+" and activeid="+activityId)==0){
		 String signinsql = "insert into sign(userid,activeid) values(?,?)";

			int result = jdbcTemplate.update(signinsql, 
					new Object[]{userId,activityId}); 
			
			return result>0;	
	}}
	return false;
}
	public Object signlist(String userId,int activeId) throws Exception {
		
			String sql="SELECT s.id,s.signtime,s.`status`,u.chineseName FROM `sign` s,`user` u WHERE s.userid=u.user_id and s.activeid="+activeId;
			if (userId!=""&&userId!=null) {
		RowMapper<User> sr=ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		User user=jdbcTemplate.query("select*from user where user_id="+userId,sr).get(0);
		System.out.println("usertype==================="+user.getUserType());
		
		if (user.getUserType().equals("1")) {
			 sql="SELECT s.id,s.signtime,s.`status`,u.chineseName FROM `sign` s,`user` u WHERE s.userid=u.user_id and s.`status`=0 and s.activeid="+activeId;
		}}
		
		return jdbcTemplate.queryForList(sql);
	}

	/* (non-Javadoc)
	 * @see com.chinasofti.service.IUserService#signAudit(int, java.lang.String)
	 */
	public Object signAudit(int userId, String idArray) throws Exception {
		// TODO Auto-generated method stub
		String signAuditsql="update `sign` s set s.`status`=1,s.approveuserid="+userId+" where s.id in("+idArray+")";
	
		return  jdbcTemplate.update(signAuditsql)>0;
	}
	//报名未签到
	public Object CountUnsign(int userId,int activeid)throws Exception{
		RowMapper<User> sr=ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		User user=jdbcTemplate.query("select*from user where user_id="+userId,sr).get(0);
		if (user.getUserType().equals("1")) {
		
		String C_regist="select count(*) from joinuser where userId="+userId;
		String c_signin="select count(*) from sign where userId= "+userId;
		int CR=jdbcTemplate.queryForInt(C_regist);
		int CS=jdbcTemplate.queryForInt(c_signin);
		
		int result= CR-CS;
		
		
		}
		return "ERROR Permission denied, Permission denied, insufficient privileges";
	}
//	public Object joinUserList(int userId)throws Exception{
//		String SELECT j.userid,u.chineseName,u.employid,u.department FROM joinuser j,user u where j.userid=u.user_id and j.userid not in (SELECT s.userid from sign s);
//		SELECT s.userid,u.chineseName,u.employid,u.department from sign s,user u where s.status=0 and s.userid=u.user_id;
//
//		return
//	}

	/* (non-Javadoc)
	 * @see com.chinasofti.service.IUserService#joinUserList(int)
	 */
	public Object joinUserList(int userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	 public List<Map<String, Object>> getJoinActiveAndNotsign()
	 {
		 String sql="SELECT u.user_id,u.chineseName,u.employid,u.department FROM joinuser j,user u where j.userid=u.user_id and j.userid not in (SELECT s.userid from sign s)";
		 List<Map<String, Object>> list= this.jdbcTemplate.queryForList(sql);
		 return list;
	 }
	 public List<Map<String, Object>> pendingaprovesign()
	 {
		 String sql ="SELECT u.user_id,u.chineseName,u.employid,u.department from sign s,user u where s.status=0 and s.userid=u.user_id";
		 List<Map<String, Object>> list=this.jdbcTemplate.queryForList(sql);
		 return list;
	 }
	 public int getJoinActiveAndNotsignCount(String userId)
	 {
		 String sql1="SELECT COUNT(j.userid)  from joinuser j WHERE j.userid="+userId;
		 String sql2="select COUNT(s.userid) from sign s where s.userid="+userId;
		int joinusercount=this.jdbcTemplate.queryForInt(sql1);
		int pendingcount=this.jdbcTemplate.queryForInt(sql2);	
		 return joinusercount-pendingcount;
	 }
}
