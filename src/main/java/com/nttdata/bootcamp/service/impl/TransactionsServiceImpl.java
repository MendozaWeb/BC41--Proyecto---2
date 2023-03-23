package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.Transaction;
import com.nttdata.bootcamp.model.TransactionRequestDto;
import com.nttdata.bootcamp.model.TransactionResponseDto;
import com.nttdata.bootcamp.repository.TransactionRepository;
import com.nttdata.bootcamp.service.TransactionsService;
import com.nttdata.bootcamp.util.Util;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Clase de implementación para la interfaz TransactionsService
 */
@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionRepository transactionsRepository;

	/**
	 * Método que devuelve todas las transacciones dentro el repositorio.
	 * @return Flowable<TransactionResponseDto>
	 */
	@Override
	public Flowable<TransactionResponseDto> getAll() {
		return Flowable.just(transactionsRepository.findAll())
				.map(Util::transactionToResponse);
	}

	/**
	 * Devuelve una transacción dentro del repositorio según el id de la transacción.
	 * @param transactionId
	 * @return Maybe<TransactionResponseDto>
	 */
	@Override
	public Maybe<TransactionResponseDto> getTransactionById(String transactionId) {
		return Maybe.just(transactionsRepository.findByTransactionId(transactionId))
				.map(Util::transactionToResponse);
	}

	/**
	 * Crea una transacción dentro del repositorio con los datos enviados en el body.
	 * @param transactionRequestDto
	 * @return Flowable<TransactionResponseDto>
	 */
	@Override
	public Maybe<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto){
		Transaction transaction = new Transaction();
		transaction.setProductType(transactionRequestDto.getProductType());
		transaction.setProductId(transactionRequestDto.getProductId());
		transaction.setCustomerId(transactionRequestDto.getCustomerId());
		transaction.setTransactionType(transactionRequestDto.getTransactionType());
		transaction.setAmount(transactionRequestDto.getAmount());
		transaction.setTransactionDate(transactionRequestDto.getTransactionDate());
		return Flowable.just(transactionsRepository.save(transaction))
				.collect(Collectors.toList())
				.map(Util::transactionToResponse)
				.toMaybe();
	}

	/**
	 * Actualiza la transacción dentro del repositorio según los datos enviados en el body.
	 * @param transactionRequestDto
	 * @return Mono<Transaction>
	 */
	@Override
	public Maybe<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto) {

		return Maybe.just(transactionsRepository.findByTransactionId(transactionRequestDto.getId()))
				.flatMap(uTransaction -> {
					Transaction transaction = new Transaction();
					transaction.setId(transactionRequestDto.getId());
					transaction.setProductType(transactionRequestDto.getProductType());
					transaction.setProductId(transactionRequestDto.getProductId());
					transaction.setCustomerId(transactionRequestDto.getCustomerId());
					transaction.setTransactionType(transactionRequestDto.getTransactionType());
					transaction.setAmount(transactionRequestDto.getAmount());
					transaction.setTransactionDate(transactionRequestDto.getTransactionDate());
					return Maybe.just(transactionsRepository.save(transaction));
				})
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::transactionToResponse)
				.toMaybe();
	}

	/**
	 * Devuelve todas las transacciones dentro del repositorio según el id del cliente.
	 * @param customerId
	 * @return Flowable<TransactionResponseDto>
	 */
	@Override
	public Flowable<TransactionResponseDto> getTransactionByCustomerId(String customerId) {
		return Flowable.just(transactionsRepository.findTransactionByCustomerId(customerId))
				.map(Util::transactionToResponse);
	}

	/**
	 * Devuelve todas las transacciones dentro del repositorio según el id del producto
	 * @param productId
	 * @return Flowable<TransactionResponseDto>
	 */
	@Override
	public Flowable<TransactionResponseDto> getTransactionByProductId(String productId) {
		return Flowable.just(transactionsRepository.findTransactionByProductId(productId))
				.map(Util::transactionToResponse);
	}

}
