package com.multithreading.main;

import java.util.ArrayList;
import java.util.List;

public class ConsumerProducer {

	private List<Integer> list = new ArrayList<>();
	private  final Integer LOWER_LIMIT=2;
	private  final Integer UPPER_LIMIT=10;
	private Object lock = new Object();
	private Integer value=3;
	
	public ConsumerProducer() {
		list.add(1);
		list.add(2);
	}
	
	public void produce() throws InterruptedException {
		
		while(true) {
			synchronized(lock) {
				if(list.size() == UPPER_LIMIT) {
					System.out.println("Waiting for Items to consumed ... ");
							lock.wait(10000);
				}else {
					list.add(value);
					System.out.println("Adding elements : " + value + " size : " + list.size());
					++value;
					//lock.notify();
				}
			}
			//Thread.sleep(2000);
		}
	}
	
	public void consume() throws InterruptedException {
		
		while(true) {
			synchronized(lock) {
				if(list.size() == LOWER_LIMIT) {
					System.out.println("Waiting for the items to be added ... ");
						lock.wait(10000);
				}else {
					list.remove(list.get(0));
					System.out.println("removing elements : " + list.get(0) + " size : " + list.size());
					//lock.notify();
				}
			}
			//Thread.sleep(2000);
		}
	}
	
	public static void main(String[] args) {

		ConsumerProducer cp = new ConsumerProducer();
		
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
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
