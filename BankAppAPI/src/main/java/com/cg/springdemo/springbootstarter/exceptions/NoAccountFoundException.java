package com.cg.springdemo.springbootstarter.exceptions;

//This class defines the implementation for NoAccountFoundException thrown by any of the controllers. 

public class NoAccountFoundException extends Exception {
	private static final long serialVersionUID = -4264778299290411620L;

	public NoAccountFoundException(String message) {
		super(message);
	}
}
