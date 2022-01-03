package com.example;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyCallableThreads {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
			var ft1 = new FutureTask<>(new SayisalLotoTask());
			var ft2 = new FutureTask<>(new SayisalLotoTask());
			var t1 = new Thread(ft1);
			var t2 = new Thread(ft2);
			t1.start();
			t2.start();
			System.err.println(ft1.get());
			System.err.println(ft2.get());
	}

}

class SayisalLotoTask implements Callable<List<Integer>> {

	@Override
	public List<Integer> call() throws Exception {
		return ThreadLocalRandom.current()
			    .ints(1, 60).distinct().limit(6)
			    .sorted().boxed().toList();
	}
	
}



