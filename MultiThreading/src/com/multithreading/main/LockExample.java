package com.multithreading.main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

	private int count;
	private Lock lock = new ReentrantLock(true);
	
	public void increment() {
		
		lock.lock();
		for(int i=0;i<10000;++i)
			++count;
		lock.unlock();
	}
	
	public static void main(String[] args) throws InterruptedException {
		LockExample le = new LockExample();
		Thread t1 = new Thread(() -> le.increment());
		Thread t2 = new Thread(() -> le.increment());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println("count : " + le.count);
	}
	
}
