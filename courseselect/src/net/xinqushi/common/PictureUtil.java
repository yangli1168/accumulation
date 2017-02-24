package net.xinqushi.common;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class PictureUtil {
	/**
	 * 
	 * @param imageFileName
	 * @param image
	 * @return url
	 */
	public static String makeDir(String imageFileName,File image) {
		// ��������ϴ��ļ��ĸ�Ŀ¼������,������Ŀ¼
		String fileName = "uploadfiles";
		String url = fileName;
		fileName = ServletActionContext.getRequest().getServletContext().getRealPath("/") + fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			file.mkdir();
		}
		Calendar cal = Calendar.getInstance();

		// ������Ŀ¼�����ڣ��������Ŀ¼
		int year = cal.get(Calendar.YEAR);
		fileName = fileName + "\\" + year;
		url = url + "\\" + year;
		file = new File(fileName);
		if (!file.exists()) {
			file.mkdir();
		}
		// ����·ݲ����ڣ������·ݵ�Ŀ¼
		int month = cal.get(Calendar.MONTH) + 1;
		fileName = fileName + "\\";
		url = url + "\\";
		if (month < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + month;
		url = url + month;
		file = new File(fileName);
		if (!file.exists()) {
			file.mkdir();
		}

		// �����ļ������ղ���
		int day = cal.get(Calendar.DAY_OF_MONTH);
		fileName = fileName + "\\";
		url = url + "\\";
		if (day < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + day;
		url = url + day;

		// �����ļ�����Сʱ����
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + hour;
		url = url + hour;

		// �����ļ����ķ��Ӳ���
		int minute = cal.get(Calendar.MINUTE);
		if (minute < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + minute;
		url = url + minute;

		// �����ļ������벿��
		int second = cal.get(Calendar.SECOND);
		if (second < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + second;
		url = url + second;

		// �����ļ����ĺ��벿��
		int millisecond = cal.get(Calendar.MILLISECOND);
		if (millisecond < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		if (millisecond < 100) {
			fileName = fileName + "0";
			url = url + "0";
		}

		fileName = fileName + millisecond;
		url = url + millisecond;

		// �����ļ�����չ������
		String extension = imageFileName.substring(imageFileName.indexOf("."));
		fileName = fileName + extension;
		url = url + extension;
		file = new File(fileName);

		// ����̨��ӡĿ¼�������á�
		System.out.println("��ƬĿ¼��" + fileName);
		
		try {
			FileUtils.copyFile(image, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// ����url
		return url;

	}
}
