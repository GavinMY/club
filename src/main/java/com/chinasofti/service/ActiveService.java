package com.chinasofti.service;

import com.chinasofti.model.Activity;

public interface ActiveService {
	public Boolean createActive(Activity active);	
	public Activity getNewActive();
	public Activity findActiveById(int activeId);   
}
