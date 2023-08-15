package com.anamika.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public boolean createDummyTransactions() {
		for(int i=0; i< 10; i++) {
			int accountId =(int) (Math.random()*1179);
			double amount = Math.random()*1000;
			Transaction transaction = new Transaction();
			transaction.setAccountId(accountId);
			transaction.setAmount(Math.round(amount));
			creditAmount(transaction);
			transaction = new Transaction();
			amount = Math.random()*1000;
			transaction.setAmount(Math.round(amount));
			transaction.setAccountId(accountId);
			debitAmount(transaction);
		}
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
    	List<Transaction> transactions = transactionRepo.findByDateBetween(stDate, enDate);
		return transactions;
	}

	@Override
	public Page<Transaction> findPaginatedTransactionBetweenDates(String startDate, String endDate, int pageNo, int pageSize) {
		LocalDate stDate = DateUtil.toLocalDate(startDate);
    	LocalDate enDate = DateUtil.toLocalDate(endDate);
    	Pageable pageable = PageRequest.of(pageNo, pageSize);
		return transactionRepo.findPaginatedTransactionBetweenDates(stDate, enDate, pageable);
	}

}
