package com.xinqushi;

import org.springframework.richclient.application.Application;
import org.springframework.richclient.application.ApplicationLauncher;

public class Main {

	/**
	 * 简单启动方式
	 */
	private static void sampleStart() {
		Application app = new Application();
		try {
			app.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		app.start();
	}

	/**
	 * 配置文件启动方式
	 */
	private static void start() {
		String[] rootApplicationContext = new String[] { "/application.xml" };
		new ApplicationLauncher("", rootApplicationContext);
	}

	public static void main(String[] args) {
		// sampleStart();

		start();
	}

}
