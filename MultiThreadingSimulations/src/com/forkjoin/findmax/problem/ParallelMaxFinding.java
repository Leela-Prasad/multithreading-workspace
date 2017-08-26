package com.forkjoin.findmax.problem;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Integer>{

	private static final long serialVersionUID = 1L;
	private Integer[] nums;
	private Integer start;
	private Integer end;
	
	public ParallelMaxFinding(Integer[] nums,Integer start,Integer end) {
		this.nums=nums;
		this.start=start;
		this.end=end;
	}
	
	@Override
	protected Integer compute() {
		if(end-start<100000) {
			return findMax(nums,start,end);
		}else {
			Integer middle = (start+end)/2;
			ParallelMaxFinding task1 = new ParallelMaxFinding(nums,start,middle);
			ParallelMaxFinding task2 = new ParallelMaxFinding(nums,middle+1,end);
			
			invokeAll(task1,task2);
			
			return Math.max(task1.join(),task2.join());
		}
		
	}
	
	private Integer findMax(Integer[] nums,int start,int end) {
		Integer max=nums[start];
		for(int i=start+1;i<=end;++i) {
			if(max<nums[i])
				max=nums[i];
		}
		return max;
	}

}
