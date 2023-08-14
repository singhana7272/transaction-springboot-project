package com.anamika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anamika.bean.Account;
import com.anamika.persistence.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public Account getAccount(int id) {
		
		return accountRepo.getById(id);
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}


}
