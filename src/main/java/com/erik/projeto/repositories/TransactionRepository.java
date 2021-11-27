package com.erik.projeto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.erik.projeto.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}