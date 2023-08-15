package com.anamika.controller;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anamika.bean.Account;
import com.anamika.bean.Transaction;
import com.anamika.service.AccountService;
import com.anamika.service.TransactionService;
import com.anamika.util.DateUtil;


@RestController
public class TransactionController  {
	
	@Autowired
	private AccountService accountService; 
	
	@Autowired
	private TransactionService transactionService;
	
	
	@GetMapping(path="/hello")
	public String hello() {
		System.out.println("hello");
		return "helloworld";
	}	 
	
	@PostMapping(path = "/accounts")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        System.out.println(account);
        accountService.saveAccount(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<Account> addAccount(Integer id){
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    
    @PostMapping(path="/transactions/credit")
    public ResponseEntity<String> creditAccount(@RequestBody Transaction transaction){
    	boolean status = transactionService.creditAmount(transaction);
    	if(status)
    		return new ResponseEntity<>("Amount credited succesfully", HttpStatus.OK);
    	return new ResponseEntity<>("Wrong input", HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping(path="/transactions/debit")
    public ResponseEntity<String> debitAccount(@RequestBody Transaction transaction){
    	boolean status = transactionService.debitAmount(transaction);
    	if(status)
    		return new ResponseEntity<>("Amount debited succesfully", HttpStatus.OK);
    	return new ResponseEntity<>("Wrong input", HttpStatus.EXPECTATION_FAILED);
    }
    
    @GetMapping(path = "/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(){
    	return new ResponseEntity<>(transactionService.getTransaction(), HttpStatus.OK);
    }
    
    @GetMapping(path= "/transactions/range")
    public ResponseEntity<List<Transaction>> getTransactionsUnderRange(String startDate, String endDate){
    	return new ResponseEntity<List<Transaction>>(transactionService.getTransactionsUnderRange(startDate, endDate), HttpStatus.OK);
    }
    
    @GetMapping(path = "createDummy")
    public String createDummyTransaction() {
    	if( transactionService.createDummyTransactions())
    			return "Dummy Transactions created successfully";
    	return "Something went wrong";
    }
    
    @GetMapping("/transactions/paginated") 
    public ResponseEntity<Page<Transaction>> findPaginatedTransactionBetweenDates(String startDate,String endDate,Integer pageNo,Integer pageSize) {
        Page<Transaction> transactions = transactionService.findPaginatedTransactionBetweenDates(startDate, endDate, pageNo, pageSize);
        return new ResponseEntity<Page<Transaction>>(transactions, HttpStatus.OK);
    }   
}
