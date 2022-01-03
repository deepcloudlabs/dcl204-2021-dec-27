package com.example;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyParallelProgramming {
	// -Xms8g -Xmx8g
	private static final int SIZE = 2_000_000_000;
	private static int[] numbers = new int[SIZE];
	static {
		System.err.println("Initializing the array...");
		IntStream.range(0, SIZE).forEach(i -> numbers[i] = i);
		System.err.println("Initializing the array...done.");
	}

	public static long serialSum() {
		var sum = 0L;
		var start = System.currentTimeMillis();
		for (var number : numbers)
			sum += number;
		var stop = System.currentTimeMillis();
		var duration = stop - start;
		System.err.printf("%-16s %8d %16d\n", "Serial sum", duration, sum);
		return sum;
	}

	public static void main(String[] args) {
		serialSum();
		parallelSum();
		streamSum();
	}

	private static void streamSum() {
		var start = System.currentTimeMillis();
		var sum = Arrays.stream(numbers).parallel().asLongStream().sum();
		var stop = System.currentTimeMillis();
		var duration = stop - start;
		System.err.printf("%-16s %8d %16d\n", "Stream api sum", duration, sum);
	}

	private static void parallelSum() {
		var fjp = ForkJoinPool.commonPool();
		var start = System.currentTimeMillis();
		var result = fjp.invoke(new SummationTask(numbers, 0, SIZE));
		var stop = System.currentTimeMillis();
		var duration = stop - start;
		System.err.printf("%-16s %8d %16d\n", "Parallel sum", duration, result);
	}

}

@SuppressWarnings("serial")
class SummationTask extends RecursiveTask<Long> {
	private int[] array;
	private int startIndex;
	private int size;

	public SummationTask(int[] array, int startIndex, int size) {
		this.array = array;
		this.startIndex = startIndex;
		this.size = size;
	}

	@Override
	protected Long compute() {
		if (size <= 2_000_000) { // serial
			var sum = 0L;
			for (int i = startIndex, j = 0; j < size; i++, j++) {
				sum += array[i];
			}
			return sum;
		} else {
			try {
				var halfSize = size / 2;
				var align = size % 2;
				var left = new SummationTask(array, startIndex, halfSize);
				var right = new SummationTask(array, startIndex + halfSize, halfSize + align);
				invokeAll(left, right);
				return left.get() + right.get();
			} catch (InterruptedException | ExecutionException e) {
				System.err.println(e.getMessage());
			}
		}
		return 0L;
	}

}