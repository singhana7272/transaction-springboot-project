package com.anamika.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.anamika.bean.Account;
import com.anamika.bean.Transaction;
import com.anamika.persistence.TransactionRepo;
import com.anamika.util.DateUtil;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionRepo transactionRepo;

	@Override
	public boolean creditAmount(Transaction transaction) {
		Account account=accountService.getAccount(transaction.getAccountId());
		if(account==null)
			return false;
		
		account.setBalance(account.getBalance()+transaction.getAmount());
		accountService.saveAccount(account);
		transaction.setTransactionType("Credit");
		
		transaction.setDate(LocalDate.now());
		transactionRepo.save(transaction);
		
		return true;
	}

	@Override
	public boolean debitAmount(Transaction transaction) {
		Account account=accountService.getAccount(transaction.getAccountId());
		if(account==null || account.getBalance() < transaction.getAmount())
			return false;
		
		
		account.setBalance(account.getBalance()-transaction.getAmount());
		accountService.saveAccount(account);
		transaction.setTransactionType("Debit");
		transaction.setDate(LocalDate.now());
		transactionRepo.save(transaction);
		return true;
	}
	
	

	@Override
	public List<Transaction> getTransaction() {
		return transactionRepo.findAll();
	}

	@Override
	
	public List<Transaction> getTransactionsUnderRange(String startDate, String endDate) {
		LocalDate stDate = DateUtil.toLocalDate(startDate);
    	LocalDate enDate = DateUtil.toLocalDate(endDate);
		return transactionRepo.findByDateBetween(stDate, enDate);
	}
	
	

}
