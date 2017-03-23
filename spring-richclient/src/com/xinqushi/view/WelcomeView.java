package com.xinqushi.view;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.springframework.richclient.application.support.AbstractView;

public class WelcomeView extends AbstractView{

	@Override
	protected JComponent createControl() {
		return new JLabel("欢迎测试");
	}
	
}
