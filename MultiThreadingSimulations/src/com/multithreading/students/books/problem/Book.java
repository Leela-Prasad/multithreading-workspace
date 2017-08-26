package com.multithreading.students.books.problem;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private Integer id;
	private Lock lock;
	
	public Book(Integer id) {
		this.id=id;
		lock=new ReentrantLock(true);
	}
	
	public void read(Student student) throws InterruptedException {
		if(lock.tryLock(1, TimeUnit.MINUTES)) {
			System.out.println(student + " is reading " + this);
			Thread.sleep(new Random().nextInt(1000));
			lock.unlock();
			System.out.println(student + "completed " + this);
		}
	}
	
	@Override
	public String toString() {
		return "Book " + id;
	}
}
