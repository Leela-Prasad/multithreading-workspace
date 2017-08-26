package com.multithreading.main;

class ProducerConsumer {
	
	public void produce() throws InterruptedException {
		
		synchronized(this) {
			System.out.println("Producer Method ...");
			wait();
			System.out.println("again producer method");
		}
		
	}
	
	public void consume() {
		synchronized(this) {
			System.out.println("Consumer method ...");
			notify();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("After sleeping");
		}
		
	}
}

public class WaitAndNotifyExample {

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumer obj = new ProducerConsumer();
		
		Thread t1 = new Thread(() -> {
			try {
				obj.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(() -> {
			obj.consume();
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}
}
