package com.example.domain;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Cat extends Animal implements Pet {
	private String name ; 
	public Cat() {
		this("Tekir");
	}
	public Cat(String name) {
		super(4);
		this.name = name;
	}

	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void play() {
		System.out.printf("%s is playing now...\n",name);
	}

	@Override
	public void eat() {
		System.out.printf("%s is eating now...\n",name);
	}

}
