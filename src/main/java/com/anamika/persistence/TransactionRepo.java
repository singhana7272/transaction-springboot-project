package com.anamika.persistence;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anamika.bean.Account;
import com.anamika.bean.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT t FROM Transaction t WHERE t.date BETWEEN :startDate AND :endDate")
	Page<Transaction> findPaginatedTransactionBetweenDates(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate,Pageable pageable);
	
}
