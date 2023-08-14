package com.anamika.service;

import com.anamika.bean.Account;

public interface AccountService {
	
	Account saveAccount(Account account);
	Account getAccount(int id);
	
}
