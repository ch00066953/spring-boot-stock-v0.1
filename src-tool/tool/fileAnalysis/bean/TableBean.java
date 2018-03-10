package tool.fileAnalysis.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 构造table方便存取数据
 * @author admin
 *
 */
public class TableBean {
	protected Map<String, Integer> colno;
	protected Map<String, Integer> rowno;
	protected List<String> colhead;
	protected List<String> rowhead;
	protected int iRow ;
	protected int iCol ;
	protected int iSize ;
	
	/*protected Map<String, Map<String,String>> table;*/
	protected List<LinkedHashMap<String,String>> list;
	protected List<List<String>> tabList; 

	public TableBean() {
		colno = new LinkedHashMap<>();
		rowno = new LinkedHashMap<>();
		colhead = new ArrayList<>();
		rowhead = new ArrayList<>();
//		table = new LinkedHashMap<>();
		list = new ArrayList<>();
		tabList = new ArrayList<>();
		iRow = 0;
		iCol = 0;
		iSize = 0;
	}
	//-----------------初始化列头begin---------------------------------
	public int initHead(LinkedHashMap<String, String> map){
		for(Entry<String, String> entry : map.entrySet()){
			colhead.add(entry.getValue());
			colno.put(entry.getValue(), iCol++);
		}
		return iCol;
	}
	public int addHead(String h){
		colhead.add(h);
		colno.put(h, iCol++);
		return iCol;
	}
	public int initHead(String h,String r){
		String[] sh = h.split(r);
		for(String s : sh){
			addHead(s);
		}
		return iCol;
	}
		
	public int initHead(String h){
		initHead(h,",");
		return iCol;
	}
	
	//-----------------初始化列头end---------------------------------
	//-----------------初始化行头begin---------------------------------
		public int initRowHead(LinkedHashMap<String, String> map){
			for(Entry<String, String> entry : map.entrySet()){
				rowhead.add(entry.getValue());
				rowno.put(entry.getValue(), iRow++);
			}
			return iRow;
		}
		public int addRowHead(String h){
			rowhead.add(h);
			rowno.put(h, iRow++);
			return iRow;
		}
		public int initRowHead(String h,String r){
			String[] sh = h.split(r);
			for(String s : sh){
				addHead(s);
			}
			return iRow;
		}
			
		public int initRowHead(String h){
			initHead(h,",");
			return iRow;
		}
		
	//-----------------初始化行头end---------------------------------
	//-----------------初始化table begin---------------------------------
	public int addTableHead(){
		int i = 0 ;
		for(String h :colhead){
			i = add(h);
		}
		return i;
	}
	public int add(String value){
		int nowCol = iSize % iCol;
//		int nowRow = sizeCount / colCount;
		LinkedHashMap<String, String> map ;
		List<String> listT;
		if(nowCol == 0){
			map = new LinkedHashMap<>();
			map.put(colhead.get(nowCol), value);
			addRowHead(value);
			list.add(map);
			
			listT = new ArrayList<>();
			listT.add(value);
			tabList.add(listT);
		}else{
			map = list.get(list.size()-1);
			map.put(colhead.get(nowCol), value);
			
			listT = tabList.get(tabList.size()-1);
			listT.add(value);
		}
		iSize++;
		return iSize;
	}
	
	public int finishRow(){
		int nowCol = iSize % iCol;
		for(;nowCol < getColCount();nowCol++)
			add("");
		return iSize;
	}
	public int add(LinkedHashMap<String,String> map){
		List<String> listT;
		
		addRowHead(map.get(colhead.get(0)));
		list.add(map);
		
		listT = new ArrayList<>();
		for(String c : colhead)
			listT.add(map.get(c));
		tabList.add(listT);
		
		iSize = iSize + map.size();
		return iSize;
	}
	
	
	public int put(String rhead,String chead,String value){
		
		return iSize;
	}
	
	//-----------------初始化table end---------------------------------
	//-----------------获取数据 begin---------------------------------
	/**
	 * 根据行列号获取内容
	 * @param iCol 列号
	 * @param iRow 行号
	 * @return
	 */
	public String getCell(int iRow, int iCol) {
		String value = tabList.get(iRow).get(iCol);
		return value;
	}

	public String getCell(int iRow, String sCol) {
		String value = list.get(iRow).get(sCol);
		return value;
	}
	
	public String getCell(String sRow, int iCol) {
		String value = list.get(rowno.get(sRow)).get(colhead.get(iCol));
		return value;
	}
	
	public String getCell(String sRow, String sCol) {
		String value = list.get(rowno.get(sRow)).get(sCol);
		return value;
	}
	
	public int getRow(String sRow) {
		return rowno.get(sRow);
	}

	public int getCol(String sCol) {
		return colno.get(sCol);
	}
	//-----------------获取数据 end---------------------------------
	public Map<String, Integer> getColno() {
		return colno;
	}

	public Map<String, Integer> getRowno() {
		return rowno;
	}

	public int getRowCount() {
		return iRow;
	}

	public int getColCount() {
		return iCol;
	}

	public List<String> getColhead() {
		return colhead;
	}
	public int getSizeCount() {
		return iSize;
	}
	public List<LinkedHashMap<String, String>> getList() {
		return list;
	}
	public List<List<String>> getTabList() {
		return tabList;
	}
	
	
}
