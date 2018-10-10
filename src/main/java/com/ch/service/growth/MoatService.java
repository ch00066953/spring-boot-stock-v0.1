package com.ch.service.growth;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.ch.component.CSVAnalysis;
import com.ch.service.CheckStock;

import download.Path;
import download.PathMap;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 护城河
 * @author admin
 *
 */
@Slf4j
@Service
public class MoatService {

	@Setter
	String stockid ;
	/**
	 * 净资产收益率
	 * @return
	 * @throws Exception 
	 */
	public String roa() throws Exception {
		Path p = PathMap.getPath("mainreport");
		p.setReMap(stockid);
		CSVAnalysis csv = new CSVAnalysis(p.getM());
		
		return null;
		
	}
	
	/**
	 * 毛利率
	 * @return
	 */
	public String gpr() {
		
		return null;
		
	}
	
	/**
	 *净利润
	 * @return
	 */
	public String net() {
		
		return null;
		
	}
	
	/**
	 * 主营业务收入
	 * @return
	 */
	public String mbi() {
		
		return null;
		
	}
	
	/**
	 * 主营业务成本
	 * @return
	 */
	public String mbc() {
		
		return null;
		
	}
	
	/**
	 * 应交税费
	 * @return
	 */
	public String taxes() {
		
		return null;
		
	}
	
	/**
	 * 总资产
	 * @return
	 */
	public String assets() {
		
		return null;
		
	}
}
