package com.cg.springdemo.springbootstarter.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bank.framework.account.pojo.BankAccount;
import com.cg.springdemo.springbootstarter.account.service.MonyMonyServiceLayer;
import com.cg.springdemo.springbootstarter.exceptions.AccountNotFoundException;
import com.cg.springdemo.springbootstarter.exceptions.NoAccountFoundException;
import com.cg.springdemo.springbootstarter.factory.MMBankFactory;

@RestController
public class BankAccountController {

	@Autowired
	private MonyMonyServiceLayer moneyMoneyServiceLayer;
	@Autowired
	private MMBankFactory mMBankFactory;
	Resources resource = null;
	
	
	private Map<String, Object> accountDetails = new HashMap<String, Object>();

	@RequestMapping("/add")
	public String addInitialAccount() {

		accountDetails.put("accountHolderName", "Amit");

		String dateOfBirth = "1995-12-15";
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dateOfBirth, formater);
		accountDetails.put("dateOfBirth", date);

		accountDetails.put("contactNumber", "9430038575");

		accountDetails.put("nationality", "Indian");

		accountDetails.put("gender", "Male");

		accountDetails.put("emailID", "kumaramit21031995@gmail.com");

		accountDetails.put("houseNo", "13");

		accountDetails.put("street", "25");

		accountDetails.put("city", "Saharsha");

		accountDetails.put("state", "Bihar");

		accountDetails.put("pinCode", "852127");

		accountDetails.put("accountType", "savingAccount");

		accountDetails.put("accountBalance", "20500");
		accountDetails.put("salary", true);
		moneyMoneyServiceLayer.createNewSavingsAccount(mMBankFactory.createNewSavingsAccount(accountDetails));

		return "Added Successfully";
	}

	@RequestMapping(value = "/accounts/{start}/{count}", method = RequestMethod.GET)
	public Resources getBankAccountByPage(@PathVariable int start, @PathVariable int count)
			throws NoAccountFoundException {
		if (moneyMoneyServiceLayer.getAllAccounts().size() == 0) {
			throw new NoAccountFoundException("The Account List is Empty");
		} else {
			List<BankAccount> listOfAllBankAccounts = new ArrayList<BankAccount>(
					moneyMoneyServiceLayer.getAllAccounts());
			List<BankAccount> tempList = new ArrayList<BankAccount>();

			for (int i = start; i < (start + count) && (i < listOfAllBankAccounts.size()); i++) {
				tempList.add(listOfAllBankAccounts.get(i - 1));
			}
			if (start == 1) {
				Link nextLink = linkTo(methodOn(this.getClass()).getBankAccountByPage(start + count, count))
						.withRel("nextLink");
				resource = new Resources(tempList, nextLink);

			} else if (start + count >= listOfAllBankAccounts.size()) {
				Link previousLink = linkTo(
						methodOn(this.getClass()).getBankAccountByPage(start - count >= 0 ? start - count : 1, count))
								.withRel("previousLink");
				resource = new Resources(tempList, previousLink);
			} else {
				Link nextLink = linkTo(methodOn(this.getClass()).getBankAccountByPage(start + count, count))
						.withRel("nextLink");
				Link previousLink = linkTo(
						methodOn(this.getClass()).getBankAccountByPage(start - count >= 0 ? start - count : 1, count))
								.withRel("previousLink");
				resource = new Resources(tempList, nextLink, previousLink);

			}
			return resource;
		}
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public Collection<BankAccount> getAllBankAccount() throws NoAccountFoundException {
		if (moneyMoneyServiceLayer.getAllAccounts().size() == 0) {
			throw new NoAccountFoundException("The Account List is Empty");
		} else {
			return moneyMoneyServiceLayer.getAllAccounts();
		}
	}

	@RequestMapping("/accounts/{accountNumberToBeSearched}")
	public Resource getAccount(@PathVariable int accountNumberToBeSearched) throws AccountNotFoundException, NoAccountFoundException {
		BankAccount bankAccount = moneyMoneyServiceLayer.getAccountByAccountNumber(accountNumberToBeSearched);
		if (bankAccount == null) {
			throw new AccountNotFoundException("The Account Requested Was Not Found ");
		}
		Link viewAllAccount = linkTo(methodOn(this.getClass()).getAllBankAccount()).withRel("View All Account List");
		
		Resource resource = new Resource(bankAccount, viewAllAccount);
		return resource;
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String createAccount(@RequestBody Map<String, Object> map) {
		if (map.get("accountType").toString().equals("savingAccount")) {
			moneyMoneyServiceLayer.createNewSavingsAccount(mMBankFactory.createNewSavingsAccount(map));
			return "Created Successfully";
		} else {
			moneyMoneyServiceLayer.createNewCurrentAccount(mMBankFactory.createNewCurrentAccount(map));
			return "Created Successfully";
		}
	}

	@RequestMapping("/update/{accountDetailsToBeUpdated}")
	public Map<String, Object> takeUpdateRequest(@PathVariable int accountDetailsToBeUpdated) {
		Map<String, Object> accountToBeUpdated = new HashMap<String, Object>();

		accountToBeUpdated.put("accountNumber",
				moneyMoneyServiceLayer.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountNumber());

		accountToBeUpdated.put("customerID", moneyMoneyServiceLayer.getAccountByAccountNumber(accountDetailsToBeUpdated)
				.getAccountHolder().getCustomerId());

		accountToBeUpdated.put("accountHolderName", moneyMoneyServiceLayer
				.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountHolder().getCustomerName());

		accountToBeUpdated.put("contactNumber", moneyMoneyServiceLayer
				.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountHolder().getContactNumber());

		accountToBeUpdated.put("dateOfBirth", moneyMoneyServiceLayer
				.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountHolder().getDateOfBirth());

		accountToBeUpdated.put("emailID", moneyMoneyServiceLayer.getAccountByAccountNumber(accountDetailsToBeUpdated)
				.getAccountHolder().getEmailId());

		return accountToBeUpdated;

	}

	@PutMapping("/update")
	public void writeUpdate(@RequestBody Map<String, Object> updatedMap) {

		moneyMoneyServiceLayer.updateCustomer(Integer.parseInt(updatedMap.get("accountNumber").toString()), updatedMap);
	}

	@RequestMapping("/*")
	public void mapAny() throws Exception {
		throw new Exception("Not A valid Request");
	}
}
