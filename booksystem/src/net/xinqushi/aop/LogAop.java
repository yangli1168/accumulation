package net.xinqushi.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
@Component
public class LogAop {
	public void myStart(JoinPoint jp){
		System.out.println("AOP --> ���õķ���ǩ��Ϊ��"+jp.getSignature());
		System.out.println("����ִ�п�ʼ......");
	}
	
	public void myEnd(){
		System.out.println("����ִ�н���......");
	}
}
