package com.example.domain;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public abstract class Animal {
	protected int legs;

	public Animal(int legs) {
		this.legs = legs;
	}

	public int getLegs() {
		return legs;
	}
	public void walk() {
		System.out.printf(
				"Animal with %d legs is walking now...\n",legs);
	}
	abstract public void eat();
}
