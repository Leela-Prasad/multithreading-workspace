package com.multithreading.dining.philosopher.problem;

import java.util.Random;

public class Philosopher implements Runnable{

	private Integer id;
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private Random random;
	private volatile boolean isFull;
	private Integer eatingCounter;
	
	public Philosopher(Integer id,Chopstick leftChopstick,Chopstick rightChopstick) {
		this.id=id;
		this.leftChopstick=leftChopstick;
		this.rightChopstick=rightChopstick;
		random = new Random();
		eatingCounter=0;
	}
	
	@Override
	public void run() {
		
		try {
			while(!isFull) {
				think();
				if(leftChopstick.pickUp(this, State.LEFT)) {
					if(rightChopstick.pickUp(this, State.RIGHT)) {
						eat();
						rightChopstick.putDown(this, State.RIGHT);
					}
					leftChopstick.putDown(this, State.LEFT);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void eat() throws InterruptedException {
		System.out.println(this + " is eating");
		++eatingCounter;
		Thread.sleep(random.nextInt(1000));
	}
	
	private void think() throws InterruptedException{
		System.out.println(this + " is thinking");
		Thread.sleep(random.nextInt(1000));
	}
	
	public void setIsFull(boolean isFull) {
		this.isFull=isFull;
	}
	
	public Integer getCounter() {
		return this.eatingCounter;
	}
	
	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}
