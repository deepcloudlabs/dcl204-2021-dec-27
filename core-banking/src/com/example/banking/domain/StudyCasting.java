package com.example.banking.domain;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyCasting {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		A a3 = new C();
		B b1 = new B();
		B b2 = new C();
		C c = new C();
//		B b3 = new A();
//		b3.gun();

	}

}

class A { void fun() {} }
class B extends A {void gun(){}}
class C extends B {}