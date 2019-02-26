package com.douzone.aoptest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
	public static void main(String[] args) 
	{
		// 클래스 패쓰에서 설정파일을 찾아서 그걸로 컨테이너생성
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml"); 
		
		ProductService ps = ac.getBean( ProductService.class);
		ProductVo vo = ps.find("TV");
		System.out.println(vo);
	}
}
