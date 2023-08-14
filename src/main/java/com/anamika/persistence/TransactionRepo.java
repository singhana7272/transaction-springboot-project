package com.anamika.persistence;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anamika.bean.Account;
import com.anamika.bean.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
