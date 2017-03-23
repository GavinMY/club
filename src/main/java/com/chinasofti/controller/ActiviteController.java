package com.chinasofti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.model.Activity;
import com.chinasofti.model.Result;
import com.chinasofti.service.Impl.ActiveServiceImpl;

@Controller
@RequestMapping("active")
public class ActiviteController {
	@Autowired
	private ActiveServiceImpl activeServiceImpl;
	@Autowired
    public Properties velocityConf;
	@ResponseBody
	@RequestMapping(value="create",method=RequestMethod.POST)
	public Object createActive(@Valid @ModelAttribute("activity") Activity activity,BindingResult bindingResult)
	{
		Result result = null;
		if(bindingResult.hasErrors())
		{
			Map<String, Object> data = new HashMap<String, Object>();			
			JSONArray errorArray=new JSONArray();
			for(ObjectError error:bindingResult.getAllErrors())
			{				
				errorArray.add(error.getDefaultMessage());				
			}
			data.put("errors", errorArray);
			result = new Result(-1, velocityConf.getProperty("golbal.error"), data);
		}	
		else
		{
		boolean flg=activeServiceImpl.createActive(activity);
		if(flg)
		{
		result = new Result(1, velocityConf.getProperty("golbal.success"), null);
		}
		else
		{
			result = new Result(-1, velocityConf.getProperty("golbal.error"), null);
		}
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("getActivity")
	public Object getActivity()
	{
		Result result =null;
		Map<String, Object> data = new HashMap<String, Object>();		
		Activity activity=activeServiceImpl.getNewActive();
		if(null!=activity)
		{
			data.put("activity", activity);
			List list=activeServiceImpl.getJoinActiveUser(activity.getId());
			data.put("joinuser", list);
			result = new Result(1, velocityConf.getProperty("golbal.success"), data);
		}
		else
		{
			result = new Result(-1,"activity not find", null);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("getJoinActiveUser")
	public Object getJoinActiveUser(int activeId)
	{
		Result result=null;
		Map<String, Object> data = new HashMap<String, Object>();
		List list=activeServiceImpl.getJoinActiveUser(activeId);
		data.put("joinuser", list);
		 result = new Result(1, velocityConf.getProperty("golbal.success"), data);
		return result;
	}
	

	
}
 