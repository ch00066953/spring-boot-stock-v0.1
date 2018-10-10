package com.ch.web;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch.mapper.UserWebCollectionMapper;
import com.ch.pojo.UserWebCollection;
import com.ch.pojo.UserWebCollectionExample;
import com.ch.pojo.UserWebCollectionExample.Criteria;

@Controller
@RequestMapping("/collection")
public class CollectionController {
	
	@Autowired
	UserWebCollectionMapper UserWebCollectionMapper;
	@Autowired
	UserWebCollectionExample example;
	
    @RequestMapping("/information")
	public String information(Locale locale, Model model) {
		return "pages/static/information";
	}
    
    @RequestMapping("/forum")
    public String forum(Locale locale, Model model) {
    	return "pages/static/forum";
    }
    
    @RequestMapping("/other")
    public String other(Locale locale, Model model) {
    	return "pages/static/other";
    }
    
    @RequestMapping("/self-collection")
    public String self(Locale locale, Model model) {
    	return "pages/self-collection";
    }
    
    @RequestMapping("/web-collection")
    public String web(Locale locale, Model model) {

    	Criteria criteria = example.createCriteria();
		criteria.andSystemtypeEqualTo("information");
		List<UserWebCollection> l= UserWebCollectionMapper.selectByExample(example);
		model.addAttribute("list", l);
    	return "pages/web-collection";
    }
    

}