package com.manish.javadev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manish.javadev.model.Account;
import com.manish.javadev.service.AccountService;

/**
 * @author Manish
 *
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public boolean ping() {
		return true;

	}

	@RequestMapping(value = "/createaccount/{accountType}/{accountHolderName}/{amount}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Account> createAccount(@PathVariable("accountType") String accountType,
			@PathVariable("accountHolderName") String accountHolderName, @PathVariable("amount") Double amount) {
		Account account = new Account(accountType, accountHolderName, amount == null ? null : amount);
		account = accountService.createAccount(account);
		return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/findaccount/{accountNumber}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Account> findAccount(@PathVariable("accountNumber") Long accountNumber) {
		Account account = accountService.findAccount(new Long(accountNumber));
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@RequestMapping(value = "/fundtransfer/{accountNumberfrom}/{accountNumberto}/{amount}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> fundTransfer(@PathVariable("accountNumberfrom") Long accountNumberFrom,
			@PathVariable("accountNumberto") Long accountNumberto, @PathVariable("amount") Double amount) {
		accountService.fundTransfer(new Long(accountNumberFrom), new Long(accountNumberto), new Double(amount));
		return new ResponseEntity<String>("Fund Transfer Successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/depositammount/{accountNumber}/{amount}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Account> depositAmount(@PathVariable("accountNumber") Long accountNumber,
			@PathVariable("amount") Double amount) {
		Account account = accountService.depositAmount(new Long(accountNumber), new Double(amount));
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

}
