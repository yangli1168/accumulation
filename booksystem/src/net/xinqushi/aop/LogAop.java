package net.xinqushi.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
@Component
public class LogAop {
	public void myStart(JoinPoint jp){
		System.out.println("AOP --> 调用的方法签名为："+jp.getSignature());
		System.out.println("方法执行开始......");
	}
	
	public void myEnd(){
		System.out.println("方法执行结束......");
	}
}
