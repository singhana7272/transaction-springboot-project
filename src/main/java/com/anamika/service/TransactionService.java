package com.anamika.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.anamika.bean.Transaction;

public interface TransactionService {
	
	boolean creditAmount(Transaction transaction);
	boolean debitAmount(Transaction transaction);
	List<Transaction> getTransaction();	
	List<Transaction> getTransactionsUnderRange(String startDate, String endDate);
	boolean createDummyTransactions();
	Page<Transaction> findPaginatedTransactionBetweenDates(String startDate, String endDate, int pageNo, int pageSize);
}
