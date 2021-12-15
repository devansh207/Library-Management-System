package com.example.demo;

import javassist.NotFoundException;

import java.util.List;

public interface TransactionService {
    public List<Transaction> findAllTransactions();

    public Transaction findTransactionById(Long id) throws NotFoundException;

    public void createTransaction(Transaction transaction);

    public void updateTransaction(Transaction transaction);

    public void deleteTransaction(Long id) throws NotFoundException;
}
