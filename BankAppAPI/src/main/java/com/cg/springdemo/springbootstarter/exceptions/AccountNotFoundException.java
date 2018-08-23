package com.cg.springdemo.springbootstarter.exceptions;

//it handles the AccountNotFoundException thrown by any of the controller.

public class AccountNotFoundException extends Exception{
	private static final long serialVersionUID = 5535428947969971730L;

	public AccountNotFoundException(String message) {
		super(message);
	}
}
