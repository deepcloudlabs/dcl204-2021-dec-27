package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import com.example.lottery.service.LotteryService;
import com.example.random.service.RandomNumberService;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StandardLotteryService implements LotteryService {
	private RandomNumberService randomNumberService ;
	
	public void setRandomNumberService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate(() -> randomNumberService.generate(max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .toList();
	}

}
