package com.cg.springdemo.springbootstarter.exceptions;

//This class provides the implementation for the InvalidAmountException thrown by any of the controller.

public class InvalidAmountException extends Exception {
	private static final long serialVersionUID = -2155515713727654528L;

	public InvalidAmountException(String message) {
		super(message);
	}
	
}
