package com.forkjoin.findmax.problem;

public class SequentialMaxFinding {

	public Integer findMax(Integer[] nums) {
		Integer max=nums[0];
		for(int i=1;i<nums.length;++i) {
			if(max<nums[i])
				max=nums[i];
		}
		return max;
	}
	
}
