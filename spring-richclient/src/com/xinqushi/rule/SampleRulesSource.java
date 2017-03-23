package com.xinqushi.rule;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.rules.Rules;
import org.springframework.rules.support.DefaultRulesSource;

import com.xinqushi.model.User;

public class SampleRulesSource extends DefaultRulesSource implements InitializingBean{

	public void afterPropertiesSet() throws Exception {
		doInit();
	}

	private void doInit() {
		Rules userRule = new Rules(User.class);
		userRule.add("firstName", userRule.maxLength(20));
		userRule.add("lastName", userRule.maxLength(20));
		userRule.add("age",userRule.range(0, 150));
		userRule.add("remark",userRule.maxLength(200));
		addRules(userRule);
	}

}
