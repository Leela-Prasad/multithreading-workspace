package com.multithreading.mergesort.problem;

import java.util.Random;

public class App {

	public static void main(String[] args) {
		Random random = new Random();
		Integer[] nums = new Integer[10000000];
		
		for(int i=0;i<nums.length;++i)
			nums[i]=random.nextInt(1000)-500;
		
		/*System.out.println("Elements Before Sorting");
		print(nums);*/
		long start1 = System.currentTimeMillis();
		new SequentialMergeSort().sort(nums);
		long end1 = System.currentTimeMillis();
		System.out.println("\nTime taken for sequential merge sort :" + (end1-start1) + "ms");
		/*System.out.println("\nElements After Sorting");
		print(nums);*/
		
		
		/*System.out.println("Elements Before Sorting");
		print(nums);*/
		long start2 = System.currentTimeMillis();
		new ParallelMergeSort().sort(nums);
		long end2 = System.currentTimeMillis();
		System.out.println("\nTime taken for Parallel merge sort :" + (end2-start2) + "ms");
		/*System.out.println("\nElements After Sorting");
		print(nums);*/
		
		
	}
	
	public static void print(Integer[] nums) {
		for(Integer num : nums)
			System.out.print(num + "\t");
	}

}
