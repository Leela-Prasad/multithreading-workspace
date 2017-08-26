package com.multithreading.mergesort.problem;

public class SequentialMergeSort {

	public void sort(Integer[] nums) {
		sort(nums,0,nums.length-1);
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
