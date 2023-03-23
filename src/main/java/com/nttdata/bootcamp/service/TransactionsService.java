package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.TransactionRequestDto;
import com.nttdata.bootcamp.model.TransactionResponseDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Clase interfaz de servicio para las Transacciones
 */
public interface TransactionsService {

	Flowable<TransactionResponseDto> getAll();

	Maybe<TransactionResponseDto> getTransactionById(String transactionId);

	Maybe<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto);

	Maybe<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto);

	Flowable<TransactionResponseDto> getTransactionByCustomerId(String customerId);

	Flowable<TransactionResponseDto> getTransactionByProductId(String productId);

}
