package com.alibaba.gt;

import java.util.concurrent.Semaphore;

/**
 * �����߳�a��b��c��������,b��c��Ҫa�̵߳�������ôʵ��
 * @author Administrator
 *
 */
public class ThreadCommunication {
	
	private static int num ;//����һ��������Ϊ����
	/**
	 * ����һ���ź���,�����ڲ�ά���˶���߳���,������������߳�,�ͷŶ���߳�,�̵߳��������ͷ���ͨ��permit������ʵ�ֵ�
	 * �߳�ͨ��semaphore.acquire()������ȡpermit,�����ǰsemaphore��permit
	 * ���������߳�,���û�����������߳�ֱ��semaphore
	 * ����release()�����ͷ�permit.
	 * ���캯���в���:permit����������
	 * 
	 * @param args
	 */
	private static Semaphore semaphore = new Semaphore(0);
	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//ģ���ʱ����֮���ʼ������num
					Thread.sleep(1000);
					num=1;
					//��ʼ����������ͷ�����permit
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
					//��ȡ permit����� semaphore û�п��õ� permit ��ȴ��������������һ��
					semaphore.acquire();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ��"+num);
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
				//��ȡ permit����� semaphore û�п��õ� permit ��ȴ��������������
			 System.out.println(Thread.currentThread().getName()+"��ȡ�� num ��ֵΪ��"+num);
			 }
			 });
		
		//ͬʱ����3���߳�
		threadA.start();
		threadB.start();
		threadC.start();
		
		
		
	}

}
