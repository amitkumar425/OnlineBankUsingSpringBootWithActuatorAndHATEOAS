package com.cg.springdemo.springbootstarter.exceptions;

//This class provides the implementation for the  InvalidAccountOrAmountException thrown by any of the 
//controller. 

public class InvalidAccountOrAmountException extends Exception {
	private static final long serialVersionUID = 7346521448709487301L;

	public  InvalidAccountOrAmountException(String message) {
		super(message);
	}
}
