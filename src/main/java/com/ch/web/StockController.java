package com.ch.web;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ch.component.CSVAnalysis;
import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfo;

import download.Path;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;
import tool.StringX;

@Controller
@RequestMapping("/stock")
@Slf4j
public class StockController {

	@Autowired
	StockInfoMapper StockInfoMapper;
	@Autowired
	CSVAnalysis CSVAnalysis;
	@RequestMapping("/stock")
	public String stock(Locale locale, Model model) throws Exception {

		Path p = PathMap.getPath("compareallpeg");
		CSVAnalysis.init(p.getM());

		model.addAttribute("rows", CSVAnalysis.getList());
		model.addAttribute("head", CSVAnalysis.getColAlias());

		return "table/thymeleaf_table";
	}

	@RequestMapping("/stock1")
	public String stock1(Locale locale, Model model) {

		return "stock1";
	}

	@RequestMapping("/load")
	public String load(Locale locale, Model model){

		return "load";
	}
	@RequestMapping("/datatable")
	public String datatable(Locale locale, Model model){
		
		return "table/jq_datatable";
	}
	@RequestMapping("/peg")
	public String peg(Locale locale, Model model){
		
		return "pages/pegs";
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET )
	public String getStockID(HttpServletRequest request,HttpServletResponse response, Model model,@PathVariable String id) throws Exception {
		log.info("=======================id="+id);
		StockInfo s = StockInfoMapper.selectByPrimaryKey(id);
		
		if(s != null){
			
			Cookie cookie = new Cookie("stockid", id.trim());
			cookie.setMaxAge(30 * 60);// 设置为30min
			cookie.setPath("/");
			response.addCookie(cookie);
			cookie = new Cookie("stockname", s.getCompabbre().replace(" ", ""));
			cookie.setMaxAge(30 * 60);// 设置为30min
			cookie.setPath("/");
			response.addCookie(cookie);
			System.out.println("已添加===============");
			model.addAttribute("stockid", id);
			model.addAttribute("stockname", s.getCompabbre());
		}else{
			Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
	        if (null==cookies) {
	            System.out.println("没有cookie=========");
	        } else {
	            for(Cookie cookie : cookies){
	            	if(cookie.getName().equals("stockid"))
	            		model.addAttribute("stockid", cookie.getValue());
	            	if(cookie.getName().equals("stockname"))
	            		model.addAttribute("stockname", cookie.getValue());
	            }
	        }
		}
		if(model.asMap().get("stockid").toString().startsWith("6"))
			model.addAttribute("stockhead", "SH");
		else
			model.addAttribute("stockhead", "SZ");
		
		return "pages/stock";
	}
}