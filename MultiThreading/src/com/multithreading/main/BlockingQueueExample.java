package com.multithreading.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

	private BlockingQueue<Integer> queue;
	
	public Producer(BlockingQueue<Integer> queue) {
		this.queue=queue;
	}
	
	@Override
	public void run() {
		int value=1;
		while(true) {
			try {
				queue.put(value);
				System.out.println("putting item to queue : "+value);
				++value;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Consumer implements Runnable {

	private BlockingQueue<Integer> queue;
	
	public Consumer(BlockingQueue<Integer> queue) {
		this.queue=queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				int value=queue.take();
				System.out.println("Removing item to queue : "+value);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
public class BlockingQueueExample {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	public static void main(String[] args) {
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}

}
