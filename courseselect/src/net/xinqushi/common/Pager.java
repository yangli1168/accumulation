package net.xinqushi.common;

public class Pager {
	// ��ǰҳ
	private int pageNo;
	// ��ҳ��
	private int pageCount;
	// ÿҳ��¼��
	private int pageSize = 3;
	// ��ť����
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
