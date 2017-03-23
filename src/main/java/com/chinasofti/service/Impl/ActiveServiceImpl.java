package com.chinasofti.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.chinasofti.model.Activity;
import com.chinasofti.service.ActiveService;

@Service
public class ActiveServiceImpl implements ActiveService {
	private static Logger logger = Logger.getLogger(ActiveServiceImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public Boolean createActive(Activity active) {
		Boolean flg=false;
	String sql="INSERT into activity(content,startime,endtime,remark,createuserid,address) values(?,?,?,?,?,?)";
	   int result=this.jdbcTemplate.update(sql, new Object[]{active.getContent(),active.getStartime(),active.getEndTime(),active.getRemark(),active.getCreateUserid(),active.getAddress()});
	   if(result>0)
	   {
		   flg=true;
	   }
		return flg;
	}
	 public static String stampToDate(String s){
	        String res;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        long lt = new Long(s);
	        Date date = new Date(lt);
	        res = simpleDateFormat.format(date);
	        return res;
	    }

	public Activity getNewActive() {
		Activity activity=null;
		RowMapper<Activity> rowMapper=ParameterizedBeanPropertyRowMapper.newInstance(Activity.class);
		String sql="SELECT * from activity ORDER BY createtime desc LIMIT ?";
		List<Activity> list= this.jdbcTemplate.query(sql, new Object[] {1}, rowMapper);
		if(list.size()>0)
		{
			activity=list.get(0);
		}
		return activity;
	}
	
	 public Activity findActiveById(int activeId)
	{
		Activity activity=null;
		RowMapper<Activity> rowMapper=ParameterizedBeanPropertyRowMapper.newInstance(Activity.class);
		String sql="SELECT * from activity where id=?";
		List<Activity> list= this.jdbcTemplate.query(sql, new Object[] {activeId}, rowMapper);
		if(list.size()>0)
		{
			activity=list.get(0);
		}
		return activity;
	}

	public List getJoinActiveUser(int activeId) {
		String sql="SELECT u.user_id,u.user_name,u.user_type,u.chineseName,u.department,u.employid,j.jointime,j.id from joinuser j,user u WHERE u.user_id=j.userid and j.activeid=? ORDER BY jointime DESC";
		List list=this.jdbcTemplate.queryForList(sql, new Object[] {activeId} );
		return list;
	}

	public Boolean ifHasJoinActive(int activeId, int userId) {
		Boolean flg=false;
		String sql="SELECT * from joinuser where activeid=? and userid=?";
		List list=this.jdbcTemplate.queryForList(sql,  new Object[] {activeId,userId});
		if(null!=list&&list.size()>0)
		{
			flg=true;
		}
		return flg;
	}

	public Boolean ifHasJoinActive(int jid) {
		Boolean flg=false;
		String sql="SELECT * from joinuser where id=?";
		List list=this.jdbcTemplate.queryForList(sql,  new Object[] {jid});
		if(null!=list&&list.size()>0)
		{
			flg=true;
		}
		return flg;
	}
	 public List<Map<String, Object>> getInvalidActive()
	 {
		 String sql="SELECT * FROM activity a where a.endtime>CURDATE() and a.status=0";
		 
		 List<Map<String, Object>> list=this.jdbcTemplate.queryForList(sql);
		 System.out.println("list:"+list.size());
		 return list;
	 }
	 
	 public boolean updateActiveSattus(int status,String activeId)
	 {
		 String sql="update activity a set a.status=? where a.id=? ";
		int result= this.jdbcTemplate.update(sql, new Object[] {status,activeId});
		if(result>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	 }

}
