package net.xinqushi.common;

import javax.servlet.http.HttpServletRequest;

public class BtnBuilder {
	/**
	 * ������ť����
	 * @param request
	 * @param pager
	 * @param page �����ҳ��
	 * @param url  ����·��
	 * @return
	 */
	public static String buildBtn(HttpServletRequest request,Pager pager,Integer page,String url){
		// ��ҳ������ťģ��
		int btns = pager.getBtnCount();
		btns = btns / 2;
		int start = page - btns;
		int end = page + btns;
		// �ж������ҳ��
		if (start < 1) {
			start = 1;
			end = start + btns * 2;
		}
		if (end > pager.getPageCount()) {
			end = pager.getPageCount();
			start = end - btns * 2;
		}
		// �������жϺ���Ȼ����С��1
		if (start < 1) {
			start = 1;
		}
		// ������ť
		StringBuilder str = new StringBuilder();
		for (int i = start; i <= end; i++) {
			str.append("<a href='" + request.getContextPath() + url + "?page=" + i + "'>");
			// ��ǰҳ��ʽ����
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
