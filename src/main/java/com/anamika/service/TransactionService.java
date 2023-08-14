package com.anamika.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.anamika.bean.Transaction;

public interface TransactionService {
	
	boolean creditAmount(Transaction transaction);
	
	boolean debitAmount(Transaction transaction);
	
	List<Transaction> getTransaction();
	
	List<Transaction> getTransactionsUnderRange(String startDate, String endDate);
}
