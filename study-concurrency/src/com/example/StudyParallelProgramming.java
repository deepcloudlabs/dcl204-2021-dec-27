package com.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyParallelProgramming {
	private static final int SIZE = 1_000_000_000;
	private static int[] numbers = new int[SIZE];
	static {
		for (var i=0;i<SIZE;++i)
			numbers[i]=i;
	}
	public static long serialSum() {
		var sum = 0L;
		var start = System.currentTimeMillis();
		for (var number : numbers)
			sum += number;
		var stop = System.currentTimeMillis();
		var duration = stop-start;
		System.err.printf("%16s %8d\n","Serial sum",duration);
		return sum;
	}
	public static void main(String[] args) {
		System.err.println(serialSum());
	}

}

class SummationTask extends RecursiveTask<Long> {
	private int []array;
	private int startIndex;
	private int size;
	
	public SummationTask(int []array, int startIndex, int size) {
		this.array = array;
		this.startIndex = startIndex;
		this.size = size;
	}

	@Override
	protected Long compute() {
		if (size<=1_000_000) { // serial
			var sum= 0L;
			for (int i=startIndex,j=0;j<size;i++,j++) {
				sum += array[i];
			}
			return sum;
		}else {
			try {
				var halfSize= size/2;
				var left = new SummationTask(array, startIndex, halfSize);
				var right = new SummationTask(array, startIndex+halfSize, halfSize);
				invokeAll(left,right);
				return left.get()+right.get();
			} catch (InterruptedException | ExecutionException e) {
				System.err.println(e.getMessage());
			}
		}
			
		return null;
	}
	
}