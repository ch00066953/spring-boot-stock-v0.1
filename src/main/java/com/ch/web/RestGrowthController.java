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
import com.ch.service.EchartsService;
import com.ch.service.EchartsService.AccessData;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.LineStyle;

import download.Path;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest/growth")
@Slf4j
public class RestGrowthController {
	@Autowired
	StockInfoMapper StockInfoMapper;
	
	@RequestMapping("/test")
	public String stock(Locale locale, Model model) throws Exception {

		EchartsService es = new EchartsService();
		//获取数据
		List<AccessData> datas = es.getWeekData();
		
		//创建Option对象
		//EnhancedOption option = new EnhancedOption();
		GsonOption option = new GsonOption(); 
		
		//设置图表标题，并且居中显示
		option.title().text("最近7天访问量图表").x(X.center);
		
		//设置图例,居中底部显示，显示边框
		option.legend().data("访问量").x(X.center).y(Y.bottom).borderWidth(1);
		
		//设置y轴为值轴，并且不显示y轴，最大值设置400，最小值-100（OSC为什么要有-100呢？）
		option.yAxis(new ValueAxis().name("IP")
				.axisLine(new AxisLine().show(true).lineStyle(new LineStyle().width(0)))
				.max(400).min(-100).interval(50));
		
		//创建类目轴，并且不显示竖着的分割线，onZero=false
		CategoryAxis categoryAxis = new CategoryAxis()
				.splitLine(new SplitLine().show(false))
				.axisLine(new AxisLine().onZero(false));
		
		//不显示表格边框，就是围着图标的方框
		option.grid().borderWidth(0);
		
		//创建Line数据
		Line line = new Line("访问量").smooth(true);
		
		//根据获取的数据赋值
		for (AccessData data : datas) {
			//增加类目，值为日期
			categoryAxis.data(data.getDate());
			
			//日期对应的数据
			line.data(data.getNums());
		}
		
		//设置x轴为类目轴
		option.xAxis(categoryAxis);
		
		//设置数据
		option.series(line);
		
		//打开浏览器预览
		log.debug(option.toString());

		return option.toString();
	}
}