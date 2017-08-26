package com.multithreading.main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerUsingLocks {

	Lock lock = new ReentrantLock(true);
	Condition condition = lock.newCondition();
	
	public void produce() throws InterruptedException {
		lock.lock();
		System.out.println("producer method...");
		condition.await();
		System.out.println("producer again ...");
		lock.unlock();
	}
	
	public void consume() throws InterruptedException {
		Thread.sleep(2000);
		lock.lock();
		System.out.println("consumer method ... ");
		condition.signal();
		System.out.println("consumer waiting to release lock");
		lock.unlock();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ConsumerProducerUsingLocks cp = new ConsumerProducerUsingLocks();
		
		Thread t1 = new Thread(() -> {
			try {
				cp.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(() -> {
			try {
				cp.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}
}
