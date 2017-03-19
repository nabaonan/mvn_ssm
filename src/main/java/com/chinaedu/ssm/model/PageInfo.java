package com.chinaedu.ssm.model;

import java.io.Serializable;

public class PageInfo implements Serializable {

	/**
	 * mysql: SELECT * FROM table LIMIT 5,10; 检索记录行 6-15 ,注意，10为查询个数
	 * 为了检索从某一个偏移量到记录集的结束所有的记录行，可以指定第二个参数为-1 SELECT * FROM table LIMIT 95,-1;
	 * 如果只给定一个参数，它表示返回最大的记录行数目： SELECT * FROM table LIMIT 5; 检索前5 个记录行
	 * 也就是说，LIMIT n 等价于 LIMIT 0,n。
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 用法：
	 * 1设置每页多少条数据
	 * 2设置当前页
	 * 3查询并设置总记录数
	 * 
	 */
	
	private int totalPage;// 总共页数

	private int currentPage;// 当前页

	private int recordsPerPage=10;// 每页的记录数 默认为10个

	private int indexStart;// 记录查询起始的坐标位置
	
	private int totalRecords;//总的记录数

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.indexStart= (currentPage - 1) * this.recordsPerPage;//设置当前页的时候就把从哪起始算出
		this.currentPage = currentPage;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getIndexStart() {
		return indexStart;
	}

	public void setIndexStart(int indexStart) {
		this.indexStart = indexStart;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalPage=(int) Math.floor(totalRecords/recordsPerPage);
		this.totalRecords = totalRecords;
	}
	
	
	

}
