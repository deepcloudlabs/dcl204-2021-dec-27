package com.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyThreadSafety {

	public static void main(String[] args) throws InterruptedException {
			var task = new Task();
			var threads = List.of(
					new Thread(task),
			        new Thread(task),
			        new Thread(task),
			        new Thread(task)
			);
			threads.forEach(Thread::start);
			for (var thread : threads)
				thread.join();
			System.err.println(task.getCount());
	}

}

class Task implements Runnable {
	// instance variable -> Heap
	// shared-memory between threads!
	//private int count;
	private AtomicInteger count = new AtomicInteger();
	@Override
	public void run() {
		for (var i=0;i<10_000;++i) {
			//synchronized(this) {
				count.incrementAndGet();			
			//}
		}
	}
	public int getCount() {
		return count.get();
	}
	
	
}
