package com.example;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyChar {

	public static void main(String[] args) {
		char c = 'x'; // 2-byte, unsigned int, unicode
		System.err.println(c+1);
		System.err.println((char)(c+1));
		c = '\u20ba';
		System.err.println(c);
	}

}
