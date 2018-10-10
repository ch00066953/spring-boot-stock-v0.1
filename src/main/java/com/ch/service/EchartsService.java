package com.ch.service;

import java.util.ArrayList;
import java.util.List;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.LineStyle;

public class EchartsService {
	/*public Option selectRemoveCauses()  {  
	    //数据库查询获取统计数据  
	    List<Map<String, Object>> list = kc22Mapper.selectRemoveCauses();  
	    //为了数据从大到小排列，这里需要倒叙  
	    Collections.sort(list, new Comparator<Map<String, Object>>() {  
	        @Override  
	        public int compare(Map<String, Object> o1, Map<String, Object> o2) {  
	            return -1;  
	        }  
	    });  
	    //创建Option  
	    Option option = new Option();  
	    option.title("剔除药品").tooltip(Trigger.axis).legend("金额（元）");  
	    //横轴为值轴  
	    option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));  
	    //创建类目轴  
	    CategoryAxis category = new CategoryAxis();  
	    //柱状数据  
	    Bar bar = new Bar("金额（元）");  
	    //饼图数据  
	    Pie pie = new Pie("金额（元）");  
	    //循环数据  
	    for (Map<String, Object> objectMap : list) {  
	        //设置类目  
	        category.data(objectMap.get("NAME"));  
	        //类目对应的柱状图  
	        bar.data(objectMap.get("TOTAL"));  
	        //饼图数据  
	        pie.data(new PieData(objectMap.get("NAME").toString(), objectMap.get("TOTAL")));  
	    }  
	    //设置类目轴  
	    option.yAxis(category);  
	    //饼图的圆心和半径  
	    pie.center(900,380).radius(100);  
	    //设置数据  
	    option.series(bar, pie);  
	    //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档  
	    option.grid().x(180);  
	    //返回Option  
	    return option;  
	}  */
	
	 //数据对象
	public class AccessData {
	    //日期
	    private String date;
	    //访问量
	    private Integer nums;

	    public AccessData(String date, Integer nums) {
	        this.date = date;
	        this.nums = nums;
	    }

	    public String getDate() {
	        return date;
	    }

	    public Integer getNums() {
	        return nums;
	    }
	}
	
	/**
	 * 模拟从数据库获取数据
	 *
	 * @return
	 */
	public List<AccessData> getWeekData() {
	    List<AccessData> list = new ArrayList<AccessData>(7);
	    list.add(new AccessData("09-16", 4));
	    list.add(new AccessData("09-17", 7));
	    list.add(new AccessData("09-18", 14));
	    list.add(new AccessData("09-19", 304));
	    list.add(new AccessData("09-20", 66));
	    list.add(new AccessData("09-21", 16));
	    list.add(new AccessData("09-22", 205));
	    return list;
	}

	public static void main(String[] args) {
		
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
				.max(400).min(-100));
		
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
		option.toString(); 
		System.out.println(option.toString());
	}
}
