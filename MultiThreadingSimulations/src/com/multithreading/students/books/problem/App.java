package com.multithreading.students.books.problem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.multithreading.common.Constants;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		Book[] books = null;
		Student[] students = null;
		ExecutorService executorService = null;
		
		try {
			executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);
			books = new Book[Constants.NUMBER_OF_BOOKS];
			students = new Student[Constants.NUMBER_OF_STUDENTS];
			
			for(int i=0;i<books.length;++i) 
				books[i]=new Book(i);
			
			for(int i=0;i<students.length;++i) {
				students[i] = new Student(i, books);
				executorService.submit(students[i]);
			}
				
			Thread.sleep(Constants.SIMULATION_RUNTIME);
			for(int i=0;i<students.length;++i)
				students[i].setIsComplete(true);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			executorService.shutdown();
			
			while(!executorService.isTerminated())
				Thread.sleep(1000);

		}
	}
}
