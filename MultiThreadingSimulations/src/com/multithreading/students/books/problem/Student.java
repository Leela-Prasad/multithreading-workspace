package com.multithreading.students.books.problem;

import java.util.Random;
import java.util.concurrent.Callable;

import com.multithreading.common.Constants;

public class Student implements Callable<Object>{

	private Integer id;
	private Book[] books;
	private boolean isComplete;
	
	public Student(Integer id,Book[] books) {
		this.id=id;
		this.books=books;
	}
	
	@Override
	public Object call() throws Exception {
		
		while(!isComplete) {
			Book book = books[new Random().nextInt(Constants.NUMBER_OF_BOOKS)];
			book.read(this);
		}
	
		return null;
	}

	public void setIsComplete(boolean isComplete) {
		this.isComplete=isComplete;
	}
	
	@Override
	public String toString() {
		return "Student " + id;
	}
	
}
