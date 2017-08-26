package com.multithreading.main;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {

	@Override
	public void run() {
		System.out.println("Job running ... ");
	}
	
}

public class ExecutorExample {

	
	public static void main(String[] args) {
		//ExecutorService es = Executors.newCachedThreadPool();
		//ExecutorService es = Executors.newFixedThreadPool(3);
		ExecutorService es = Executors.newSingleThreadExecutor();
		
		for(int i=0;i<10;++i)
			es.submit(new Worker());
		System.out.println("finished ...");
		
	}

}
