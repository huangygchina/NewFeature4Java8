package com.huang.java8.study;

public interface PersonFactory<P extends Person> {
	
	P create(String firstName, String lastName);

}