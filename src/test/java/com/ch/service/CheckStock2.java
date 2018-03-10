package com.ch.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfo;

import download.Path;
import download.PathMap;
import tool.DownLoad;
import tool.fileAnalysis.XlsxReader;


@Service
public class CheckStock2 {
	
	@Autowired
	StockInfoMapper StockInfoMapper;

	public void downloanSZ() throws IOException{
		DownLoad.downLoanPath("szstock","");
	}
	
	public void downloanSH() throws IOException{
		DownLoad.downLoanPath("shstock","");
	}
	
	public void importSZ() throws IOException {
		Path p = PathMap.getPath("szstock");
		XlsxReader xr = new XlsxReader(p.getMain());
		for(int i = 1;xr.getRowCount() >= i ; i++){
			String no = xr.getCell(i, 0);
			if(StockInfoMapper.selectByPrimaryKey(no) != null){
				continue;
			}
			
			StockInfo si = new StockInfo(no);
			si.setCompabbre(xr.getCell(i, 1));
			si.setCompabbre(xr.getCell(i, 2));
			si.setEnglishname(xr.getCell(i, 3));
			si.setRegadd(xr.getCell(i, 4));
			si.setEnglishname(xr.getCell(i, 5));
			si.setSharescode(xr.getCell(i, 6));
			si.setSharesaddre(xr.getCell(i, 7));
			si.setTimemarket(xr.getCell(i, 8));
			si.setTotalequity(xr.getCell(i, 9));
			si.setFlowequity(xr.getCell(i, 10));
			si.setBsharescode(xr.getCell(i, 11));
			StockInfoMapper.insert(si);
		}

	}
	
	public void importSH() {
		// TODO Auto-generated method stub
		
	}
}
