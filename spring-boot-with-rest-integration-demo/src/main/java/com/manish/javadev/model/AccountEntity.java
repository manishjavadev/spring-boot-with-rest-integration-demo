package com.manish.javadev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Manish
 *
 */
@Entity
@Table(name = "TBL_ACCOUNT")
public class AccountEntity {

	@Id
	@Column(name = "ACC_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "ACCOUNT_HOLDER_NAME")
	private String accountHolderName;

	@Column(name = "AMOUNT")
	private Double amount;

	public AccountEntity() {
		super();
	}

	public AccountEntity(String accountType, String accountHolderName, Double amount) {
		super();
		this.accountType = accountType;
		this.accountHolderName = accountHolderName;
		this.amount = amount;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", accountHolderName="
				+ accountHolderName + ", amount=" + amount + "]";
	}

}