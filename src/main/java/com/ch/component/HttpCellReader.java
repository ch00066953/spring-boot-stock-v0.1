package com.ch.component;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import download.Path;

@Component
public class HttpCellReader {

	protected String text;
	
	public void readHtmlCell(Path p,String para) throws IOException {
		readHtmlCell(p.getM(),para);
	}
			
	public void readHtmlCell(String url,String para) throws IOException {
//		url = "http://basic.10jqka.com.cn/600887/equity.html#astockchange";
		Document doc;
		if(url.startsWith("http"))
			doc = Jsoup.connect(url).get();
		else{
			File f = new File(url);
			doc = Jsoup.parse(f, "gbk", "");
		}
		text = doc.select(para).text();
	}

	public String getText() {
		return text;
	}
	
}
