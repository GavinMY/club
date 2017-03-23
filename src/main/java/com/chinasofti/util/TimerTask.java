package com.chinasofti.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chinasofti.service.Impl.ActiveServiceImpl;

@Component
public class TimerTask {
	private static Logger logger = Logger
			.getLogger(TimerTask.class);

	@Autowired
	ActiveServiceImpl activeServiceImpl;
	@Scheduled(cron="0/10 * *  * * ? ")   //每5秒执行一次    
	public void updateEinvoce() {
		logger.debug("Timer Task is begin work");
		List<Map<String, Object>> list=activeServiceImpl.getInvalidActive();
		for(Map map:list)
		{
			logger.debug("activeId():"+map.get("id").toString()+"will be delete");
			activeServiceImpl.updateActiveSattus(1,map.get("id").toString());
		}
	}

}
