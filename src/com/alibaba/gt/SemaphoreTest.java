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
			//����һ������
			semaphore.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName()+"������");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"����");
		//�ͷ�һ������
		semaphore.release();

	}

}
