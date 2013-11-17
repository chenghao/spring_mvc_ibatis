package com.lovo.pojo;

public class Page extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pageNo;//第几页
	private Integer pageSize;//每页显示多少条记录
	private Integer pageTotal;//一共多少页

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}
}
