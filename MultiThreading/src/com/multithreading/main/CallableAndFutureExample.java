package com.multithreading.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Worker2 implements Callable<String> {

	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "Id : " + id;
	}

}

public class CallableAndFutureExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newCachedThreadPool();
		List<String> output = new ArrayList<>();
		
		for(int i=0;i<5;++i) {
			Worker2 worker = new Worker2();
			worker.setId(i+1);
			Future<String> future= es.submit(worker);
			output.add(future.get());
		}
		
		es.shutdown();
		System.out.println(output);
		Thread.sleep(5000);
		System.out.println("finished");
	}
}
