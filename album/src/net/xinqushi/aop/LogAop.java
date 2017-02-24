package net.xinqushi.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class LogAop {
	public void myBefore(JoinPoint jp) {
		System.out.println("AOP --> 调用的方法签名为：" + jp.getSignature() + " --> " + jp.getTarget());
		System.out.println("方法执行开始......");
	}

	public void myBehind() {
		System.out.println("方法执行结束......");
	}
}
