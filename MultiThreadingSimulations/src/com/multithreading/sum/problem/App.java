package com.multithreading.sum.problem;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {
		Integer numOfProcessors = Runtime.getRuntime().availableProcessors();
		
		Integer[] nums=new Integer[10000000];
		
		for(int i=0;i<nums.length;++i)
			nums[i]=new Random().nextInt(1000);
		
		SequentialSum sequentialSum = new SequentialSum();
		long start = System.currentTimeMillis();
		Long seqsum = sequentialSum.findSum(nums);
		long end = System.currentTimeMillis();
		System.out.println("Sequential sum : " + seqsum);
		System.out.println("Time taken to execute Sequential sum :" + (end-start) + "ms");
		
		long start2 = System.currentTimeMillis();
		Integer splitLength = (int)(Math.ceil((nums.length*1.0)/numOfProcessors));
		ExecutorService executorService = Executors.newFixedThreadPool(numOfProcessors); 
		Long[] partialSums = new Long[numOfProcessors];
		Long parallelsum=0L; 
		
		try {
			for(int i=0;i<numOfProcessors;++i) {
				partialSums[i] = executorService.submit(new ParallelSum(nums,i*splitLength,(i+1)*splitLength)).get();
			}
			executorService.shutdown();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			executorService.shutdownNow();
		}
		
		for(Long partialSum : partialSums) {
			parallelsum+=partialSum;
		}
		
		long end2 = System.currentTimeMillis();
		System.out.println("Parallel sum :"+parallelsum);
		System.out.println("Time taken to execute Parallel Sum :" + (end2-start2)+"ms");
		
			
		
	}

}
