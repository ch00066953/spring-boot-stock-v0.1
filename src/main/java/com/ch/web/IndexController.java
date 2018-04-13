package com.ch.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
    @RequestMapping("/index")
	public String index(Locale locale, Model model) {

		return "index";
	}
    

    @RequestMapping("/home")
	public String home(Locale locale, Model model) {
		return "home";
	}
    
    @RequestMapping("/test")  
    public String hello(Model map){  
        Map user= new HashMap();  
        user.put("name", "姓名");  
        user.put("age", "年龄");  
        user.put("sex", "性别");  
        user.put("birthday", "生日");  
        user.put("phonenumber", "手机");  
        map.addAttribute("userhead", user);  
        List userinfo =new ArrayList();  
        userinfo.add("周美玲");  
        userinfo.add("32");  
        userinfo.add("女");  
        userinfo.add("1985");  
        userinfo.add("19850014665");  
        map.addAttribute("userinfo", userinfo);  
        List outerList=new ArrayList<>();  
        Map innerMap=new HashMap<>();  
        for (int i = 0; i < 10; i++) {  
            innerMap.put("1", i);  
            innerMap.put("1", i++);  
            i++;  
            outerList.add(innerMap);  
        }  
        map.addAttribute("listmap", outerList);  
        return "/test";  
    } 

}