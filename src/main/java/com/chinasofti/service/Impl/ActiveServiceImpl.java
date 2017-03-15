package com.chinasofti.service.Impl;

import java.util.List;

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
	@Override
	public Boolean createActive(Activity active) {
		Boolean flg=false;
	String sql="INSERT into activity(content,startime,endtime,remark,createuserid,address) values(?,?,?,?,?,?)";
	   int result=this.jdbcTemplate.update(sql, new Object[]{active.getContent(),active.getStartTime(),active.getEndTime(),active.getRemark(),active.getCreateUserid(),active.getAddress()});
	   if(result>0)
	   {
		   flg=true;
	   }
		return flg;
	}

	@Override
	public Activity getNewActive() {
		Activity activity=null;
		RowMapper<Activity> rowMapper=ParameterizedBeanPropertyRowMapper.newInstance(Activity.class);
		String sql="SELECT * from activity ORDER BY createtime desc LIMIT ?";
		List<Activity> list= this.jdbcTemplate.query(sql, new Object[] {1}, rowMapper);
		if(list.size()>0)
		{
			activity=list.get(0);
//			Map activityMap=(Map) list.get(0);
//			activity=new Activity();
//			activity.setAddress(address);
//			activity.setContent(content);
//			activity.setCreateTime(createTime);
			
//			System.out.println(activityMap.get("remark"));
//			System.out.println(activityMap.get("content"));
		}
		return activity;
	}
	
	

}
