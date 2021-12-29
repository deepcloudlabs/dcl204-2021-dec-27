package com.example.banking.application;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyInheritance {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		A a = new A();	
		E e = new E(42);
	}

}
// class A {}
class A extends Object {
	public A() { // no-arg constructor
		super();
	}
}

class E {
	public E(int x) {}
}
class F extends E {
	public F() {
		super(42);
	}
}