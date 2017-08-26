package com.multithreading.main;

public class SynchronizedBlockExample {

	private static int counter1;
	private static int counter2;
	
	Object lock1 = new Object();
	Object lock2 = new Object();
	
	public void addCount1() {
		synchronized(lock1) {
			++counter1;
		}
		
	}
	
	public void addCount2() {
		synchronized(lock2) {
			++counter2;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		SynchronizedBlockExample obj = new SynchronizedBlockExample();
		
		Thread t1 = new Thread(() -> {
			for(int i=0;i<1000;++i)
				obj.addCount1();
		});
		
		Thread t2 = new Thread(() -> {
			for(int i=0;i<1000;++i)
				obj.addCount2();
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("count1 :" + counter1);
		System.out.println("count2 :" + counter2);
	}

}
