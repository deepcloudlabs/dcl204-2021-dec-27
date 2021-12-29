package com.example.random.service.business;

import java.util.concurrent.ThreadLocalRandom;

import com.example.random.service.RandomNumberService;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
