package com.ch.component;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import download.Path;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpCellReaderTest {

	@Autowired
	HttpCellReader HttpCellReader;
	@Test
	public void testReadHtmlCellPathString() throws IOException {
		Path p = PathMap.getPath("industry");
		p.setReMap("000001");
		HttpCellReader.readHtmlCell(p, "p.threecate.fl span.tip.f14");
		log.info(HttpCellReader.getText());
	}

	@Test
	public void testReadHtmlCellStringString() throws IOException {
		String p = "C:\\Users\\admin\\Desktop\\深华发A(000020)_行业对比_F10_同花顺金融服务网.html";
		HttpCellReader.readHtmlCell(p, "p.threecate.fl");
		log.info(HttpCellReader.getText());
	}

	@Test
	public void testGetText() {
		fail("Not yet implemented");
	}

}
