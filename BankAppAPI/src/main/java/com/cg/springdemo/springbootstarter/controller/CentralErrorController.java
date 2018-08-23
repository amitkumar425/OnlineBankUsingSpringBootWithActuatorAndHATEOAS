package com.cg.springdemo.springbootstarter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.springdemo.springbootstarter.account.pojo.ErrorResponse;
import com.cg.springdemo.springbootstarter.exceptions.AccountNotFoundException;
import com.cg.springdemo.springbootstarter.exceptions.InvalidAccountNumberException;
import com.cg.springdemo.springbootstarter.exceptions.InvalidAccountOrAmountException;
import com.cg.springdemo.springbootstarter.exceptions.InvalidAmountException;
import com.cg.springdemo.springbootstarter.exceptions.NoAccountFoundException;

//This class acts as the destination for any type of exception in any controller. 
//It handles five kind of exceptions defined.
@ControllerAdvice
public class CentralErrorController {
	private ErrorResponse errorResponse = new ErrorResponse();

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundError(AccountNotFoundException exception) {
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoAccountFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoAccountFoundError(NoAccountFoundException exception) {
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidAccountNumberException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAccountNumberException(InvalidAccountNumberException exception) {
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(exception.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAmountException(InvalidAmountException exception) {
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(exception.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidAccountOrAmountException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAccountOrAmountException(InvalidAccountOrAmountException exception) {
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(exception.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleOtherException(Exception exception){
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}

}
