package com.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyAsyncProgramming {

	public static void main(String[] args) throws InterruptedException {
			var lotteryService = new LotteryService();
			lotteryService.draw(60, 6)
			              .thenAcceptAsync(
			           (numbers) -> {
			        	   System.err.printf("%s: %s\n",
			        			   Thread.currentThread().getName(),
			        			   numbers
			        			   );
			           });
			TimeUnit.SECONDS.sleep(3);
			System.err.printf("%s: Application is done.",
					Thread.currentThread().getName());
	}

}

class LotteryService {
	public CompletableFuture<List<Integer>> draw(int max,int size){
		return CompletableFuture.supplyAsync(
				() -> {
					System.err.println(Thread.currentThread().getName());			
					return ThreadLocalRandom.current()
					.ints(1, max).distinct().limit(size)
					.sorted().boxed().toList(); 
				});
	}
}