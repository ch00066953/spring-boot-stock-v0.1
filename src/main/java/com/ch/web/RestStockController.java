package com.ch.web;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ch.component.CSVAnalysis;
import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfo;

import download.Path;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest")
@Slf4j
public class RestStockController {
	@Autowired
	StockInfoMapper StockInfoMapper;
	
	@RequestMapping("/reststock")
	public List<Map> stock(Locale locale, Model model) throws Exception {

		CSVAnalysis CSVAnalysis = new CSVAnalysis();
		Path p = PathMap.getPath("compareallpeg");
		CSVAnalysis.init(p.getM());
//		List<Map> m = (List<Map>) new LinkedHashMap();

		CSVAnalysis.getList().remove(0);
		
		model.addAttribute("rows", CSVAnalysis.getList());
		model.addAttribute("head", CSVAnalysis.getColAlias());

		return CSVAnalysis.getList();
	}
	@RequestMapping("/reststock1")
	public Map stock1(Locale locale, Model model) throws Exception {
		
		CSVAnalysis CSVAnalysis = new CSVAnalysis();
		
		Path p = PathMap.getPath("compareallpeg");
		CSVAnalysis.init(p.getM());
//		List<Map> m = (List<Map>) new LinkedHashMap();
		
		List<Map> m = CSVAnalysis.getList();
		
		model.addAttribute("rows", CSVAnalysis.getList());
		model.addAttribute("head", CSVAnalysis.getColAlias());
		
		return CSVAnalysis.getColAlias();
	}
	
	@RequestMapping("/reststock3")
	public List<Map> stock3(Locale locale, Model model) throws Exception {

		CSVAnalysis CSVAnalysis = new CSVAnalysis();
		Path p = PathMap.getPath("compareallpeg");
		CSVAnalysis.init(p.getM());
//		List<Map> m = (List<Map>) new LinkedHashMap();

		CSVAnalysis.getList().remove(0);
		
		model.addAttribute("data", CSVAnalysis.getList());
		model.addAttribute("head", CSVAnalysis.getColAlias());

		return CSVAnalysis.getList();
	}
	
	@RequestMapping("/totalstockpegs")
	public Map totalStockPegs(Locale locale, Model model) throws Exception {
		
		CSVAnalysis CSVAnalysis = new CSVAnalysis();
		Path p = PathMap.getPath("compareallpeg");
		CSVAnalysis.init(p.getM());
		Map m = new HashMap<>();
		m.put("data", CSVAnalysis.getList());
		m.put("draw", 1);
		m.put("recordsTotal", CSVAnalysis.getList().size());
		m.put("recordsFiltered", CSVAnalysis.getList().size());
		
		CSVAnalysis.getList().remove(0);
		
		return m;
	}

	@RequestMapping(value="/{id}",method = RequestMethod.GET )
	public Map getRestStockID(@PathVariable String id) throws Exception {
		
		log.info("getRestStockID:"+id);
		StockInfo s = StockInfoMapper.selectByPrimaryKey(id);
		Map m = new HashMap<>();
		
		if(s != null){
			Path p = PathMap.getPath("pegs");
			p.setReMap(id);
			CSVAnalysis CSVAnalysis = new CSVAnalysis();
			CSVAnalysis.init(p.getM());
			
			m.put("data", CSVAnalysis.getList());
			m.put("draw", 1);
			m.put("recordsTotal", CSVAnalysis.getList().size());
			m.put("recordsFiltered", CSVAnalysis.getList().size());
			
			CSVAnalysis.getList().remove(0);
			
		}
		return m;
	}
}