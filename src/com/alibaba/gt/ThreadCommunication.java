package com.alibaba.gt;

import java.util.concurrent.Semaphore;

/**
 * 三个线程a、b、c并发运行,b、c需要a线程的数据怎么实现
 * @author Administrator
 *
 */
public class ThreadCommunication {
	
	private static int num ;//定义一个变量作为数据
	/**
	 * 定义一个信号量,该类内部维持了多个线程锁,可以阻塞多个线程,释放多个线程,线程的阻塞和释放是通过permit概念来实现的
	 * 线程通过semaphore.acquire()方法获取permit,如果当前semaphore有permit
	 * 则分配给该线程,如果没有则阻塞该线程直到semaphore
	 * 调用release()方法释放permit.
	 * 构造函数中参数:permit（允许）个数
	 * 
	 * @param args
	 */
	private static Semaphore semaphore = new Semaphore(0);
	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//模拟耗时操作之后初始化变量num
					Thread.sleep(1000);
					num=1;
					//初始化完参数后释放两个permit
					semaphore.release(2);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		Thread threadB = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//获取 permit，如果 semaphore 没有可用的 permit 则等待，如果有则消耗一个
					semaphore.acquire();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName()+"获取到 num 的值为："+num);
				 }
		});
		
		Thread threadC = new Thread(new Runnable() {
			
			 @Override
			 public void run() {
				 try {
					 semaphore.acquire();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//获取 permit，如果 semaphore 没有可用的 permit 则等待，如果有则消耗
			 System.out.println(Thread.currentThread().getName()+"获取到 num 的值为："+num);
			 }
			 });
		
		//同时开启3个线程
		threadA.start();
		threadB.start();
		threadC.start();
		
		
		
	}

}
