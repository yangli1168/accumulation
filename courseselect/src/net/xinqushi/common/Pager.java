package net.xinqushi.common;

public class Pager {
	// 当前页
	private int pageNo;
	// 总页数
	private int pageCount;
	// 每页记录数
	private int pageSize = 3;
	// 按钮个数
	private int btnCount = 5;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBtnCount() {
		return btnCount;
	}

	public void setBtnCount(int btnCount) {
		this.btnCount = btnCount;
	}

}
