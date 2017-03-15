package com.chinasofti.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.model.Activity;
import com.chinasofti.model.Result;
import com.chinasofti.service.Impl.ActiveServiceImpl;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("active")
public class ActiviteController {
	@Autowired
	private ActiveServiceImpl activeServiceImpl;
	@Autowired
    public Properties velocityConf;
	@ResponseBody
	@RequestMapping("create")
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
			result = new Result(1, velocityConf.getProperty("golbal.success"), data);
		}
		else
		{
			result = new Result(-1,"activity not find", null);
		}
		return result;
	}
	
}
 