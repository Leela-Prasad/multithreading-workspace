package com.multithreading.dining.philosopher.problem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.multithreading.common.Constants;

public class App {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = null;
		Philosopher[] philosophers = null;
		
		try {
			executorService=Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
			
			Chopstick[] chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];
			for(int i=0;i<chopsticks.length;++i) {
				chopsticks[i] = new Chopstick(i);
			}
			
			philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
			for(int i=0;i<philosophers.length;++i) {
				philosophers[i]= new Philosopher(i, chopsticks[i],chopsticks[(i+1)%Constants.NUMBER_OF_CHOPSTICKS]);
				executorService.execute(philosophers[i]);
			}
			
			Thread.sleep(Constants.SIMULATION_RUNTIME);
			
			for(Philosopher aPhilosopher : philosophers) {
				aPhilosopher.setIsFull(true);
			}
			
		}finally {
			executorService.shutdown();
			
			while(!(executorService.isTerminated())) 
				Thread.sleep(1000);
			
			for(Philosopher aPhilosopher : philosophers) {
				System.out.println(aPhilosopher + " eats " + aPhilosopher.getCounter());
			}
		}
		
	}

}
