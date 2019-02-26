package com.douzone.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.BeforeAdvice;
import org.springframework.stereotype.Component;

@Aspect // 이거만 달아놓는다고해서 스캐닝 되지않음
@Component
public class MyAspect 
{
	@Before("execution(ProductVo com.douzone.aoptest.ProductService.find(..) )") // 포인트컷 지정
	// 접근자 생략가능
	
	public void BeforeAdvice()
	{
		System.out.println("call [before acvice]");
	}
	
	@After("execution(* *..*.ProductService.*(..))")  // *..* : 모든 패키지
	public void afterAdvice()
	{
		System.out.println("call [after acvice]");
	}
	
	@AfterReturning("execution(* *..*.ProductService.*(..))")  // *..* : 모든 패키지
	public void afterReturningAdvice()
	{
		System.out.println("call [afterReturning acvice]");
	}
	
	@AfterThrowing(value ="execution(* *..*.ProductService.*(..))", throwing = "ex")  // *..* : 모든 패키지 /  글로벌 처리
	public void afterThrowingAdvice(Throwable ex)
	{
		System.out.println("call [afterThrowing acvice] : " + ex);
	}
	
	@Around("execution(* *..*.ProductService.*(..))")  // *..* : 모든 패키지 /  글로벌 처리
	public Object aroundAdvice(ProceedingJoinPoint pjp) // before + after  포인트컷을 aspect에서 실행시킬수있도록 객체(ProceedingJoinPoint)를 넘겨줘야함
		throws Throwable
	{		
		// before
		System.out.println("call [around acvice] : before");

		// Point cut이 되는 메소드 호출
		Object[] parameters = { "camera" };
		Object result = pjp.proceed(parameters); // 핵심메소드 호출이 일어남  / 반환타입이 있는놈이라 result에 담아서 리턴 반환이없으면 null
		
		// after
		System.out.println("call [around acvice] : after");

		
		// around의 장점 : 핵심메소드를 중간에 호출할수도있고, 파라미터를 바꿔버릴수도있음
		
		return result;
	}
}
