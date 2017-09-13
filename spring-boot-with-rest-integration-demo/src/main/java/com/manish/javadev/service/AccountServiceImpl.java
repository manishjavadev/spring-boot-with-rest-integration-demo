package com.manish.javadev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manish.javadev.dao.AccountDao;
import com.manish.javadev.model.AccountEntity;

/**
 * @author Manish
 *
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	public void setPersonDAO(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public AccountEntity createAccount(AccountEntity accountEntity) {
		AccountEntity acccountResult = accountDao.save(accountEntity);
		return acccountResult;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void fundTransfer(Long accountFrom, Long accountTo, Double amount) {
		AccountEntity accountEntity = accountDao.findOne(accountFrom);
		accountEntity.setAmount(accountEntity.getAmount() - amount);
		accountDao.save(accountEntity);
		accountEntity = accountDao.findOne(accountTo);
		accountEntity.setAmount(accountEntity.getAmount() + amount);
		accountDao.save(accountEntity);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public AccountEntity depositAmount(Long accountNumber, Double amount) {
		AccountEntity accountEntity = accountDao.findOne(accountNumber);
		accountEntity.setAmount(accountEntity.getAmount() + amount);
		return accountDao.save(accountEntity);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public AccountEntity findAccount(Long accountNumber) {
		return accountDao.findOne(accountNumber);
	}

}
