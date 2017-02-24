package net.xinqushi.common;

import javax.servlet.http.HttpServletRequest;

public class BtnBuilder {
	/**
	 * 导航按钮生成
	 * @param request
	 * @param pager
	 * @param page 请求的页码
	 * @param url  请求路径
	 * @return
	 */
	public static String buildBtn(HttpServletRequest request,Pager pager,Integer page,String url){
		// 分页导航按钮模块
		int btns = pager.getBtnCount();
		btns = btns / 2;
		int start = page - btns;
		int end = page + btns;
		// 判断输出的页码
		if (start < 1) {
			start = 1;
			end = start + btns * 2;
		}
		if (end > pager.getPageCount()) {
			end = pager.getPageCount();
			start = end - btns * 2;
		}
		// 经两次判断后仍然可能小于1
		if (start < 1) {
			start = 1;
		}
		// 构建按钮
		StringBuilder str = new StringBuilder();
		for (int i = start; i <= end; i++) {
			str.append("<a href='" + request.getContextPath() + url + "?page=" + i + "'>");
			// 当前页格式设置
			if (i == page) {
				str.append("<font color='red'><b>");
				str.append(i);
				str.append("</b></font>");
			} else {
				str.append(i);
			}
			str.append("</a>");
			str.append("&nbsp;&nbsp;&nbsp;");
		}
		str.append("<br/>");
		
		return str.toString();
	}
}
