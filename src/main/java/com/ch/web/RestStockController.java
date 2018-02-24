package com.ch.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tool.fileAnalysis.action.PESerialCalc;

@RestController
public class RestStockController {
	
	  @RequestMapping("/reststock")
		public List<Map> stock(Locale locale, Model model) throws Exception {

		  	PESerialCalc p = new PESerialCalc("300070");
			p.setPerNetP("235000");//万
			p.runFAll();
			
			model.addAttribute("rows",p.exportData);
			model.addAttribute("head",p.map);

			return p.exportData;
		}

}