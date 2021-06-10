package com.example.demo;

@SuppressWarnings("serial")
public class MyCustomException extends RuntimeException {

	public MyCustomException(String msg) {
		super(msg);
	}

}
