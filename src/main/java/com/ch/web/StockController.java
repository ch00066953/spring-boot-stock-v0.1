package com.ch.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tool.fileAnalysis.action.PESerialCalc;

@Controller
public class StockController {
	
	  @RequestMapping("/stock")
		public String stock(Locale locale, Model model) throws Exception {

		  PESerialCalc p = new PESerialCalc("300070");
			p.setPerNetP("235000");//万
			p.runFAll();
			
			model.addAttribute("rows",p.exportData);
			model.addAttribute("head",p.map);

			return "pages/stock";
		}

}