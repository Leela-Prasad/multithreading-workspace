package com.forkjoin.findmax.problem;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {
		Random random = new Random();
		
		Integer[] nums = new Integer[10000000];
		for(int i=0;i<nums.length;++i) {
			nums[i]=random.nextInt(1000);
		}
		
		long start1 = System.currentTimeMillis();
		System.out.println(new SequentialMaxFinding().findMax(nums));
		long end1 = System.currentTimeMillis();
		System.out.println("Time taken to find maximum sequentially :"+ (end1 - start1) + "ms");
		
		long start2 = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		System.out.println(pool.invoke(new ParallelMaxFinding(nums,0,nums.length-1)));
		long end2 = System.currentTimeMillis();
		System.out.println("Time taken to find maximum Parallely :"+ (end2 - start2) + "ms");
		
	}
	
}
