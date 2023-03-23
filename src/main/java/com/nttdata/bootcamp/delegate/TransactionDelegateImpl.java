package com.nttdata.bootcamp.delegate;

import com.nttdata.bootcamp.api.TransactionApiDelegate;
import com.nttdata.bootcamp.model.TransactionRequestDto;
import com.nttdata.bootcamp.model.TransactionResponseDto;
import com.nttdata.bootcamp.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionDelegateImpl implements TransactionApiDelegate {

    @Autowired
    private TransactionsService transactionsService;

    /**
     * Create a new customer company in the bank
     * @param transactionRequestDto
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionsService.createTransaction(transactionRequestDto).blockingGet());
    }

    /**
     * Find All Transaction
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponseDto> findAllTransaction() {
        return ResponseEntity.ok(transactionsService.getAll().blockingFirst());
    }

    /**
     * Find Transaction by customerId
     * @param customerId  (required)
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponseDto> findTransactionByCustomerId(String customerId) {
        return ResponseEntity.ok(transactionsService.getTransactionByCustomerId(customerId).blockingFirst());
    }

    /**
     * Find Transaction by customerId
     * @param transactionId  (required)
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponseDto> findTransactionById(String transactionId) {
        return ResponseEntity.ok(transactionsService.getTransactionById(transactionId).blockingGet());
    }

    /**
     * Find Transaction by customerId
     * @param productId  (required)
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponseDto> findTransactionByProductId(String productId) {
        return ResponseEntity.ok(transactionsService.getTransactionByProductId(productId).blockingFirst());
    }

    /**
     * Update an existent transaction in the bank
     * @param transactionRequestDto  (required)
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionsService.updateTransaction(transactionRequestDto).blockingGet());
    }
}
