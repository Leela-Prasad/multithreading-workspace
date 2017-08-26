package com.multithreading.mergesort.problem;

class Worker implements Runnable {

	private Integer[] nums;
	private Integer start;
	private Integer end;
	private Integer nThreads;
	
	public Worker(Integer[] nums,Integer start,Integer end,Integer nThreads) {
		this.nums=nums;
		this.start=start;
		this.end=end;
		this.nThreads=nThreads;
	}
	
	@Override
	public void run() {
		new ParallelMergeSort().sort(nums,start,end,nThreads);
	}
	
}
public class ParallelMergeSort {

	public void sort(Integer[] nums) {
		Integer nThreads = Runtime.getRuntime().availableProcessors();
		sort(nums,0,nums.length-1,nThreads);
	}

	public void sort(Integer[] nums, Integer start, Integer end,Integer nThreads) {
		if(nThreads<=1) {
			sort(nums,start,end);
			return;
		}
			
		Integer middle = (start+end)/2;
		
		Thread leftSorter = new Thread(new Worker(nums,start,middle,nThreads/2));
		Thread rightSorter = new Thread(new Worker(nums,middle+1,end,nThreads/2));
		leftSorter.start();
		rightSorter.start();
		
		try {
			leftSorter.join();
			rightSorter.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		merge(nums,start,middle,end);
	}
	
	private void sort(Integer[] nums, Integer start, Integer end) {
		if(start>=end)
			return;
		Integer middle = (start+end)/2;
		
		sort(nums,start,middle);
		sort(nums,middle+1,end);
		merge(nums,start,middle,end);
	}

	private void merge(Integer[] nums, Integer start, Integer middle, Integer end) {
		
		Integer[] left = new Integer[middle-start+1];
		Integer[] right = new Integer[end-middle];
		
		for(int i=0;i<left.length;++i)
			left[i]=nums[start+i];
		
		for(int i=0;i<right.length;++i)
			right[i]=nums[middle+1+i];
		
		int i=0,j=0,k=start;
		while(i<left.length&&j<right.length) {
			if(left[i]<=right[j])
				nums[k++]=left[i++];
			else
				nums[k++]=right[j++];
		}
		
		if(i>=left.length) {
			while(j<right.length) {
				nums[k++]=right[j++];
			}
		}
		
		if(j>=right.length) {
			while(i<left.length) {
				nums[k++]=left[i++];
			}
		}
	}
	
}
