//This class extends the CurrentAccount .
//it has specific instances related to MMBankCurrentAccount.
//This Class implements the withdraw logic for current account.

package com.cg.springdemo.springbootstarter.account.pojo;

import com.cg.bank.framework.account.pojo.BankAccount;
import com.cg.bank.framework.account.pojo.CurrentAccount;
import com.cg.bank.framework.account.pojo.Customer;


public class MMBankCurrentAccount extends CurrentAccount implements Comparable<BankAccount>{

	
	public MMBankCurrentAccount(Customer accountHolder, double accountBalance, double odLimit, String accountType) {
		super(accountHolder, accountBalance, odLimit, accountType);
	}

	@Override
	public int compareTo(BankAccount currentAccount) {
		return this.getAccountNumber()-currentAccount.getAccountNumber();
	}
	
	public double withdraw(double amountToBeWithDrawn) {
		if(this.getAccountBalance()+this.getOdLimit()>=amountToBeWithDrawn) {
			this.setAccountBalance(this.getAccountBalance()-amountToBeWithDrawn);
			return amountToBeWithDrawn;
		}else {
			return -1;
		}
	}
}
