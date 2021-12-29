package com.example.lottery.service;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public interface LotteryService {
		List<Integer> draw(int max,int size);
		default List<List<Integer>> draw(int max,int size,int column){
			return IntStream.range(0, column)
			         .mapToObj( i -> draw(max,size))
			         .toList();
		}
}
