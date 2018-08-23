//This class extends the BankAccountCollection .
//It also defines various methods specific to the Money money bank.
package com.cg.springdemo.springbootstarter.account.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cg.bank.framework.account.dao.BankAccountCollection;
import com.cg.bank.framework.account.pojo.BankAccount;
import com.cg.bank.framework.account.pojo.Customer;

@Repository
public class MoneyMoneyBankCollection extends BankAccountCollection {

	// This method removes the bank bank account from the Database.
	public void removeBankAccountByAccountNumber(int accountNumberToBeRemoved) {
		for (BankAccount bankAccount : BankAccountCollection.viewAll()) {
			if (bankAccount.getAccountNumber() == accountNumberToBeRemoved) {
				BankAccountCollection.viewAll().remove(bankAccount);
				break;
			}
		}
	}

	// It fetches the account by the account number.
	public BankAccount getAccountByAccountNumber(int accountToBeSearched) {
		for (BankAccount bankAccount : BankAccountCollection.viewAll()) {
			if (bankAccount.getAccountNumber() == accountToBeSearched) {
				return bankAccount;
			}
		}
		return null;
	}

	// it fetches the customer list associated with the MMBank
	public Collection<Customer> getCustomers() {
		List<Customer> listOfCustomer = new ArrayList<Customer>();
		for (BankAccount s : viewAll()) {
			listOfCustomer.add(s.getAccountHolder());
		}
		return listOfCustomer;
	}

	public Collection<BankAccount> getAllAccounts() {
		List<BankAccount> listOfCustomer = new ArrayList<BankAccount>();
		for (BankAccount s : viewAll()) {
			listOfCustomer.add(s);
		}
		return listOfCustomer;
	}

	// It uses the withdraw() method of the BankAccount and performs the withdrawal
	// action on a given account.
	public double withdrawAmount(int accountToDeductedFrom, double amount) {
		for (BankAccount b : viewAll()) {
			if ((b.getAccountNumber() == accountToDeductedFrom)) {
				if (b.withdraw(amount) != -1) {
					return amount;
				}
			}
		}
		return -1;
	}

	// It uses the deposit() method of the BankAccount and performs the deposit
	// action on a given account.
	public double depositAmount(int accountToBeDepositedIn, double amount) {
		for (BankAccount b : viewAll()) {
			if ((b.getAccountNumber() == accountToBeDepositedIn)) {
				b.deposit(amount);
				return amount;
			}
		}
		return -1;

	}

	public double performFundTransfer(int receipientAccountNumber, int donerAccountNumber,
			double amountToBeTransffered) {

		for (BankAccount receiver : viewAll()) {
			if (receiver.getAccountNumber() == receipientAccountNumber) {
				for (BankAccount sender : viewAll()) {
					if (sender.getAccountNumber() == donerAccountNumber) {
						double valid = receiver.deposit(sender.withdraw(amountToBeTransffered));
						if (valid != -1)
							return amountToBeTransffered;
					}
				}
			}
		}
		return -1;
	}

	public boolean validateUser(int accountNumber) {
		for (BankAccount b : viewAll()) {
			if ((b.getAccountNumber() == accountNumber)) {
				return true;
			}
		}

		return false;
	}

	public Map<Integer, Integer> withdrawWithDenomination(int accountNumber, double amountToBeWithdrawn) {
		Map<Integer, Integer> denomination = new HashMap<Integer, Integer>();
		double amountWithdrawn = withdrawAmount(accountNumber, amountToBeWithdrawn);

		if (amountWithdrawn != -1) {
			if (amountWithdrawn >= 2000) {
				denomination.put(2000, (int) (amountWithdrawn / 2000));
				amountWithdrawn = amountWithdrawn % 2000;
			}
			if (amountWithdrawn >= 500) {
				denomination.put(500, (int) (amountWithdrawn / 500));
				amountWithdrawn = amountWithdrawn % 500;
			}
			if (amountWithdrawn >= 200) {
				denomination.put(200, (int) (amountWithdrawn / 200));
				amountWithdrawn = amountWithdrawn % 200;
			}
			if (amountWithdrawn >= 100) {
				denomination.put(100, (int) (amountWithdrawn / 100));
				amountWithdrawn = amountWithdrawn % 100;
			}
			if (amountWithdrawn >= 50) {
				denomination.put(50, (int) (amountWithdrawn / 50));
				amountWithdrawn = amountWithdrawn % 50;
			}
			if (amountWithdrawn >= 10) {
				denomination.put(10, (int) (amountWithdrawn / 10));
				amountWithdrawn = amountWithdrawn % 10;
			}
			if (amountWithdrawn >= 1) {
				denomination.put(1, (int) (amountWithdrawn / 1));
			}
		}
		return denomination;

	}

}
