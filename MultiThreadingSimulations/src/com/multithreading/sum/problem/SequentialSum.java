package com.multithreading.sum.problem;

public class SequentialSum {

	public Long findSum(Integer[] nums) {
		Long sum=0L;
		for(Integer num : nums)
			sum +=num;
		return sum;
	}
}
