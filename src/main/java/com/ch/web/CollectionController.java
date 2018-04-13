package com.ch.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/collection")
public class CollectionController {
	
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
    	return "pages/static/self-collection";
    }
    

}