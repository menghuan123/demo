package com.alibaba.gt;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	private static Semaphore semaphore = new Semaphore(5,true);
	public static void main(String[] args) {
		for(int i =0;i<100;i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					test();

				}

			}).start();
		}

	}


	public static void test() {
		try {
			//申请一个请求
			semaphore.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName()+"进来了");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"走了");
		//释放一个请求
		semaphore.release();

	}

}
