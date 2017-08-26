package com.multithreading.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Producer2 implements Runnable {

	private BlockingQueue<String> queue;
	
	public Producer2(BlockingQueue<String> queue) {
		this.queue=queue;
	}
	
	@Override
	public void run() {
		try {
			queue.put("F");
			queue.put("B");
			queue.put("Z");
			queue.put("H");
			queue.put("I");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Consumer2 implements Runnable {

	private BlockingQueue<String> queue;
	
	public Consumer2(BlockingQueue<String> queue) {
		this.queue=queue;
	}
	
	@Override
	public void run() {
		while(!(queue.isEmpty())) {
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class PriorityBlockingQueueExample {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new PriorityBlockingQueue<>();
		new Thread(new Producer2(queue)).start();
		new Thread(new Consumer2(queue)).start();
	}
}
