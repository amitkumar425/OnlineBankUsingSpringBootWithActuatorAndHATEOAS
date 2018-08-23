package com.cg.springdemo.springbootstarter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springdemo.springbootstarter.account.service.MonyMonyServiceLayer;
import com.cg.springdemo.springbootstarter.exceptions.InvalidAccountNumberException;
import com.cg.springdemo.springbootstarter.exceptions.InvalidAccountOrAmountException;
import com.cg.springdemo.springbootstarter.exceptions.InvalidAmountException;

@RestController
public class TransactionController {

	@Autowired
	private MonyMonyServiceLayer moneyMoneyServiceLayer;

	@RequestMapping(value = "/withdraw/{accountNumber}/{amountToBeWithdrawn}", method = RequestMethod.PUT)
	public Map<Integer, Integer> doWithdraw(@PathVariable int accountNumber, @PathVariable double amountToBeWithdrawn)
			throws InvalidAmountException, InvalidAccountOrAmountException {

		if (!((Object) amountToBeWithdrawn instanceof Double) || amountToBeWithdrawn < 0) {
			throw new InvalidAmountException("The Entered Amount is Invalid");
		}

		Map<Integer, Integer> map = moneyMoneyServiceLayer.withdrawWithDenomination(accountNumber, amountToBeWithdrawn);
		if(map.isEmpty()==true) {
			throw new InvalidAccountOrAmountException("The amount Or Account number provided is invalid");
		}
		else {
			return map;
		}
	}

	@RequestMapping(value = "/deposit/{accountToBeDepsitedIn}/{amount}", method = RequestMethod.PUT)
	public double dodeposit(@PathVariable int accountToBeDepsitedIn, @PathVariable double amount)
			throws InvalidAccountNumberException, InvalidAmountException {
		if (!((Object) amount instanceof Double) || amount < 0) {
			throw new InvalidAmountException("The Entered Amount is Invalid");
		}

		double amountDeposited = moneyMoneyServiceLayer.depositAmount(accountToBeDepsitedIn, amount);

		if (amountDeposited == -1) {
			throw new InvalidAccountNumberException("Invalid Account Number");
		} else {
			return amount;
		}
	}

//	@RequestMapping(value = "/fundtransfer/{receipientAccountNumber}/{donerAccountNumber}/{amountToBeTransffered}", method = RequestMethod.PUT)
//	public String doFundTransfer(@PathVariable int receipientAccountNumber, @PathVariable int donerAccountNumber,
//			@PathVariable double amountToBeTransffered) {
//		double amount = moneyMoneyServiceLayer.performFundTransfer(receipientAccountNumber, donerAccountNumber,
//				amountToBeTransffered);
//		if (amount != -1)
//			return amount + " Transferred from " + donerAccountNumber + " to " + receipientAccountNumber
//					+ " Successfully";
//		else
//			return "Fund Transfer Failed";
//	}

}
