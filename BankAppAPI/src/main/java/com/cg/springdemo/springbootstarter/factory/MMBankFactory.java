//This class implements the class BankFactory.
//It overrides the the methods createNewCurrentAccount() and createNewSavingsAccount() of class BankFactory.

package com.cg.springdemo.springbootstarter.factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cg.bank.framework.account.pojo.CurrentAccount;
import com.cg.bank.framework.account.pojo.SavingsAccount;
import com.cg.bank.framework.factory.BankFactory;
import com.cg.springdemo.springbootstarter.account.pojo.MMBankCurrentAccount;
import com.cg.springdemo.springbootstarter.account.pojo.MMBankCustomer;
import com.cg.springdemo.springbootstarter.account.pojo.MMBankCustomerAddress;
import com.cg.springdemo.springbootstarter.account.pojo.MMBankSavingsAccount;

@Service
public class MMBankFactory extends BankFactory {
	
	MMBankCurrentAccount mmBankCurrentAccount;
	MMBankSavingsAccount mmBankSavingAccount;
	MMBankCustomer mmBankCustomer;
	MMBankCustomerAddress mmBankCustomerAddress;

	@Override
	public CurrentAccount createNewCurrentAccount(Map<String, Object> map) {
		mmBankCustomerAddress = new MMBankCustomerAddress(map.get("houseNo").toString(), map.get("street").toString(),
				map.get("city").toString(), map.get("state").toString(), Integer.parseInt(map.get("pinCode").toString()));

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateOfBirth = LocalDate.parse(map.get("dateOfBirth").toString(),dtf);
		
		mmBankCustomer = new MMBankCustomer(map.get("accountHolderName").toString(), Long.parseLong(map.get("contactNumber").toString()),
				 map.get("emailID").toString(), dateOfBirth, mmBankCustomerAddress, map.get("nationality").toString(),
				map.get("gender").toString());

		mmBankCurrentAccount = new MMBankCurrentAccount(mmBankCustomer, Double.parseDouble(map.get("accountBalance").toString()),
				Double.parseDouble(map.get("odLimit").toString()), map.get("accountType").toString());
		return mmBankCurrentAccount;
	}

	@Override
	public SavingsAccount createNewSavingsAccount(Map<String, Object> map) {
			
			mmBankCustomerAddress = new MMBankCustomerAddress(map.get("houseNo").toString(),
					map.get("street").toString(), map.get("city").toString(), map.get("state").toString(),
					 Integer.parseInt(map.get("pinCode").toString()));
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateOfBirth = LocalDate.parse(map.get("dateOfBirth").toString(),dtf);
			
			mmBankCustomer = new MMBankCustomer(map.get("accountHolderName").toString(),
					Long.parseLong(map.get("contactNumber").toString()),  map.get("emailID").toString(), dateOfBirth, mmBankCustomerAddress,
					map.get("nationality").toString(), map.get("gender").toString());
			mmBankSavingAccount = new MMBankSavingsAccount(mmBankCustomer, Double.parseDouble(map.get("accountBalance").toString()),
					Boolean.parseBoolean(map.get("salary").toString()), map.get("accountType").toString());
			
		return mmBankSavingAccount;
	}

}
