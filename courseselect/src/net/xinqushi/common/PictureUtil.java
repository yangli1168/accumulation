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
		// 如果保存上传文件的根目录不存在,创建根目录
		String fileName = "uploadfiles";
		String url = fileName;
		fileName = ServletActionContext.getRequest().getServletContext().getRealPath("/") + fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			file.mkdir();
		}
		Calendar cal = Calendar.getInstance();

		// 如果年的目录不存在，创建年的目录
		int year = cal.get(Calendar.YEAR);
		fileName = fileName + "\\" + year;
		url = url + "\\" + year;
		file = new File(fileName);
		if (!file.exists()) {
			file.mkdir();
		}
		// 如果月份不存在，创建月份的目录
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

		// 生成文件名的日部分
		int day = cal.get(Calendar.DAY_OF_MONTH);
		fileName = fileName + "\\";
		url = url + "\\";
		if (day < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + day;
		url = url + day;

		// 生成文件名的小时部分
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + hour;
		url = url + hour;

		// 生成文件名的分钟部分
		int minute = cal.get(Calendar.MINUTE);
		if (minute < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + minute;
		url = url + minute;

		// 生成文件名的秒部分
		int second = cal.get(Calendar.SECOND);
		if (second < 10) {
			fileName = fileName + "0";
			url = url + "0";
		}
		fileName = fileName + second;
		url = url + second;

		// 生成文件名的毫秒部分
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

		// 生成文件的扩展名部分
		String extension = imageFileName.substring(imageFileName.indexOf("."));
		fileName = fileName + extension;
		url = url + extension;
		file = new File(fileName);

		// 控制台打印目录【测试用】
		System.out.println("照片目录：" + fileName);
		
		try {
			FileUtils.copyFile(image, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// 返回url
		return url;

	}
}
