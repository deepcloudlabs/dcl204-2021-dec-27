package com.example;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyRunnableThreads {

	public static void main(String[] args) throws InterruptedException {
		var task1 = new LotteryTask();
		var task2 = new LotteryTask();
		var t1 = new Thread(task1);
		var t2 = new Thread(task2);
		t1.start();
		t2.start();
		// do not do this! --> t1.run(); // main-thread
		t1.join(); // main-thread blocks until t1 finishes its job!
		System.err.println(task1.getNumbers());
		t2.join();
		System.err.println(task2.getNumbers());
	}

}

class LotteryTask implements Runnable {
	private List<Integer> numbers;
	@Override
	public void run() {
		numbers = ThreadLocalRandom.current()
				    .ints(1, 60).distinct().limit(6)
				    .sorted().boxed().toList();
	}
	public List<Integer> getNumbers() {
		return numbers;
	}
	
}
