package com.example.demo;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Transaction> findAllTransactions(){
        return transactionRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Transaction findTransactionById(Long id) throws NotFoundException{
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Transaction not found with ID %d", id)));
    }

    @Override
    public void createTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) throws NotFoundException{
        final Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Transaction not found with ID %d", id)));
        System.out.println(transaction);

        transactionRepository.deleteById(transaction.getId());
    }
}
