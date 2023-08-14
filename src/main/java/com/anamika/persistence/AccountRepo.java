package com.anamika.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anamika.bean.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

}
