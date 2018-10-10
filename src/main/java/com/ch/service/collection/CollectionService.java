package com.ch.service.collection;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.mapper.UserWebCollectionMapper;
import com.ch.pojo.UserWebCollection;
import com.ch.pojo.UserWebCollectionExample;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tool.StringX;

@Service
@Slf4j
public class CollectionService {
	@Autowired
	UserWebCollectionMapper UserWebCollectionMapper;
	@Setter
	UserWebCollectionExample Example;
	
	public void addCollection(String url,String title,String usertype,String userid,String systemtype) {
		String website = url,mainstie="",iconsite = "",
				description = "",keywords = "",status="1";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements head=doc.select("head");
			URL u = new URL(url);
			mainstie = "//"+u.getHost();
			if(head.select("link[rel=icon]").size()==0)
				iconsite = mainstie+"/favicon.ico";
			else
				iconsite = head.select("link[rel=icon]").get(0).attr("href");
			
			keywords = head.select("meta[name=keywords]").get(0).attr("content");
			description = head.select("meta[name=description]").get(0).attr("content");
			if(StringX.isEmpty(title))
				title = head.select("title").get(0).text();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserWebCollection uwc = new UserWebCollection();
		uwc.setWebsite(website);
		uwc.setMainstie(mainstie);
		uwc.setIconsite(iconsite);
		uwc.setTitle(title);
		uwc.setDescription(description);
		uwc.setKeywords(keywords);
		uwc.setUsertype(usertype);
		uwc.setUserid(userid);
		uwc.setSystemtype(systemtype);
		uwc.setStatus(status);
		UserWebCollectionMapper.insert(uwc);
	}
	
}
