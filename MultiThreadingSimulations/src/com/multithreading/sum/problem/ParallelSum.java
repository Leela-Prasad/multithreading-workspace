package com.multithreading.sum.problem;

import java.util.concurrent.Callable;

public class ParallelSum implements Callable<Long>{

	private Integer start;
	private Integer end;
	private Integer[] nums;
	private Long partialSum;
	
	public ParallelSum(Integer[] nums,Integer start,Integer end) {
		this.start=start;
		this.end=end;
		this.nums=nums;
		this.partialSum=0L;
	}
	
	@Override
	public Long call() throws Exception {
		
		for(int i=start;(i<nums.length&&i<end);++i)
			partialSum+=nums[i];
		
		return partialSum;
	}

}
