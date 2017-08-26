package com.multithreading.main;

class Runner5  implements Runnable{

	private boolean isTerminated=false;
	private Integer threadNumber;
	
	@Override
	public void run() {
		while(!isTerminated) {
			System.out.println("Thread " + threadNumber + " Running");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	public Integer getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}
	
}

public class VolatileExample {
	
	public static void main(String[] args) throws InterruptedException {
		Runner5 runner = new Runner5();
		runner.setThreadNumber(1);
		Thread t = new Thread(runner);
		
		Runner5 runner2 = new Runner5();
		runner2.setThreadNumber(2);
		Thread t2 = new Thread(runner2);
		
		t.start();
		t2.start();
		
		Thread.sleep(300);
		runner.setTerminated(true);
		runner2.setTerminated(true);
		//t.join();
		//t2.join();
		
		System.out.println("Finished...");
	}
}
