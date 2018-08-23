//This class maintains the Customer details of a bank.
//It extends the Customer and just calls the parent class constructor using super keyword.
package com.cg.springdemo.springbootstarter.account.pojo;

import java.time.LocalDate;

import com.cg.bank.framework.account.pojo.Address;
import com.cg.bank.framework.account.pojo.Customer;



public class MMBankCustomer extends Customer {

	public MMBankCustomer(String customerName, long contactNumber, String emailId, LocalDate dateOfBirth,
			Address permanentAddress, String nationality, String gender) {
		super(customerName, contactNumber, emailId, dateOfBirth, permanentAddress, nationality, gender);
	}
	
	
}
