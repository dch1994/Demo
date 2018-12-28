package com.hxzy.store.domain;

import java.util.List;

public class PageModel {
	//每页显示的条数
	private int pageSize=12;
	//当前页数
	private int currentPageNum;
	//总记录条数
	private int totalRecords;
	//总页数
	private int totalPageNum;
	//每页开始的索引
	private int startIndex;
	/*第一页 limit 0,3
	第二页 limit 3,3
	第三页 limit 6,3
	第四页 limit 9,3 ()*/
	//上一页
	private int prePageNum;
	//下一页
	private int nextPageNum;
	
	private List list;
	
	private String url;

	                     //当前页数 2      //总记录条数 10       //每页显示的条数 3
	public PageModel(int currentPageNum,int totalRecords,int pageSize ){
		this.currentPageNum=currentPageNum;
		this.totalRecords=totalRecords;
		this.pageSize=pageSize;
		//第二页 limit 3,3
		this.startIndex=pageSize*(currentPageNum-1);  //3*(2-1) =3
		//第三页 limit 6,3               //3*2=6
		
		
		//总页数 4页
		this.totalPageNum=totalRecords%pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1);
		
	}
	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPrePageNum() {
		//1-1 =0
		prePageNum=currentPageNum-1;
		if(prePageNum<1){
			prePageNum=1;
			
		}
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		nextPageNum=currentPageNum+1;
		if(nextPageNum>totalPageNum){
			nextPageNum=totalPageNum;
			
		}
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
