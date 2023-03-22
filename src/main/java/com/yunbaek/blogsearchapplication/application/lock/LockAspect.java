package com.yunbaek.blogsearchapplication.application.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LockAspect {

	private final Lock lock = new ReentrantLock();

	@Pointcut("@annotation(com.yunbaek.blogsearchapplication.application.lock.Lock)")
	public void lockPointcut() {
	}

	@Around("lockPointcut()")
	public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
		lock.lock();
		try {
			return joinPoint.proceed();
		} finally {
			lock.unlock();
		}
	}
}
