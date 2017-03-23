package com.chinasofti.service;

import java.util.List;
import java.util.Map;

import com.chinasofti.model.Activity;

public interface ActiveService {
	public Boolean createActive(Activity active);	
	public Activity getNewActive();
	public Activity findActiveById(int activeId); 
	public List getJoinActiveUser(int activeId);
	public Boolean ifHasJoinActive(int activeId,int userId);
	public Boolean ifHasJoinActive(int jid);
	public List<Map<String, Object>> getInvalidActive();
	public boolean updateActiveSattus(int status,String activeId);
}
