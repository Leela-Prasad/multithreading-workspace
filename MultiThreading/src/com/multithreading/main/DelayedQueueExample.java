package com.multithreading.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {

	private long duration;
	private String message;
	
	public DelayedWorker(long duration,String message) {
		this.duration=System.currentTimeMillis() + duration;
		this.message=message;
	}
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int compareTo(Delayed obj) {
		if(this.duration < ((DelayedWorker)obj).getDuration())
			return -1;
		else if(this.duration > ((DelayedWorker)obj).getDuration())
			return +1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
	@Override
	public String toString() {
		return message;
	}
	
}

public class DelayedQueueExample {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
		queue.put(new DelayedWorker(1000,"First Message"));
		queue.put(new DelayedWorker(10000,"Second Message"));
		queue.put(new DelayedWorker(5000,"Third Message"));
		queue.put(new DelayedWorker(10,"Fourth Message"));
		
		while(!(queue.isEmpty())) {
			System.out.println(queue.take());
		}
		
	}

}
