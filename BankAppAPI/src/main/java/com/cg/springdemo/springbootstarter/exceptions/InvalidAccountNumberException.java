package com.cg.springdemo.springbootstarter.exceptions;

//This class defines the InvalidAccountNumberException thrown by any of the controllers.

public class InvalidAccountNumberException extends Exception {
	private static final long serialVersionUID = 4683704551131976033L;

	public InvalidAccountNumberException(String message) {
		super(message);
	}
	
}
