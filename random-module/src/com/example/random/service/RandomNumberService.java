package com.example.random.service;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public interface RandomNumberService {
		int generate(int min, int max);
		default int generate(int max) {
			return generate(1,max);
		}
}
