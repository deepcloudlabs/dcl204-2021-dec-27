package com.example;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyRunnableThreads {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int cpus = Runtime.getRuntime().availableProcessors();
		var threadPool = Executors.newFixedThreadPool(cpus);
		var task1 = new LotteryTask();
		var task2 = new LotteryTask();
		var future1 = threadPool.submit(task1);
		var future2 = threadPool.submit(task2);
		future1.get(); // main-thread blocks until t1 finishes its job!
		System.err.println(task1.getNumbers());
		future2.get();
		System.err.println(task2.getNumbers());
		threadPool.shutdown();
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
