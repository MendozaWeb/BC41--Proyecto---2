package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.CreditCard;
import com.nttdata.bootcamp.model.CreditCardConsumeRequestDto;
import com.nttdata.bootcamp.model.CreditCardConsumeResponseDto;
import com.nttdata.bootcamp.model.CreditCardPayRequestDto;
import com.nttdata.bootcamp.model.CreditCardPayResponseDto;
import com.nttdata.bootcamp.model.CreditCardRequestDto;
import com.nttdata.bootcamp.model.CreditCardResponseDto;
import com.nttdata.bootcamp.repository.CreditCardRepository;
import com.nttdata.bootcamp.service.CreditCardService;
import com.nttdata.bootcamp.util.Util;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
    private CreditCardRepository creditCardRepository;

	/**
	 * Método que devuelve todas las tarjetas de credito dentro el repositorio.
	 * @return Flowable<CreditCardResponseDto>
	 */
	@Override
	public Flowable<CreditCardResponseDto> getAll() {
		return Flowable.just(creditCardRepository.findAll())
				.map(Util::creditCardToResponse);
	}

	/**
	 * Devuelve todas las tarjetas de credito dentro el repositorio segun el id de la tarjeta.
	 * @return Maybe<CreditCardResponseDto>
	 */
	@Override
	public Maybe<CreditCardResponseDto> getCreditCardById(String creditCardId) {
		return Flowable.just(creditCardRepository.findByCreditCardId(creditCardId))
				.map(Util::creditCardToResponse)
				.firstElement();
	}

	/**
	 * Crea una nueva tarjeta de credito dentro del repositorio con los datos enviados en el body.
	 * @param creditCardRequestDto
	 * @return Flowable<CreditCardResponseDto>
	 */
	@Override
	public Maybe<CreditCardResponseDto> createCreditCard(CreditCardRequestDto creditCardRequestDto) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCustomerId(creditCardRequestDto.getCustomerId());
		creditCard.setCreditLine(creditCardRequestDto.getCreditLine());
		creditCard.setCreditCardAccount(creditCardRequestDto.getCreditCardAccount());
		creditCard.setCreditCardId(creditCardRequestDto.getCreditCardId());
		creditCard.setLineAvailable(creditCardRequestDto.getLineAvailable());
		creditCard.setFeeMonth(creditCardRequestDto.getFeeMonth());
		creditCard.setMinimumAmount(creditCardRequestDto.getMinimumAmount());
		creditCard.setSettlementAmount(creditCardRequestDto.getSettlementAmount());
		creditCard.setPayDay(creditCardRequestDto.getPayDay());
		creditCard.setCreateDate(creditCardRequestDto.getCreateDate());
		return Flowable.just(creditCardRepository.save(creditCard))
				.collect(Collectors.toList())
				.map(Util::creditCardToResponse)
				.toMaybe();
	}

	/**
	 * Actualiza una tarjeta de credito dentro del repositorio segun los datos enviados en el body.
	 * @param creditCardRequestDto
	 * @return Maybe<CreditCardResponseDto>
	 */
	@Override
	public Maybe<CreditCardResponseDto> updateCreditCard(CreditCardRequestDto creditCardRequestDto) {
		return Maybe.just(creditCardRepository.findByCreditCardId(creditCardRequestDto.getId()))
				.flatMap(uCreditCard -> {
					CreditCard creditCard = new CreditCard();
					creditCard.setId(creditCardRequestDto.getId());
					creditCard.setCustomerId(creditCardRequestDto.getCustomerId());
					creditCard.setCreditLine(creditCardRequestDto.getCreditLine());
					creditCard.setCreditCardAccount(creditCardRequestDto.getCreditCardAccount());
					creditCard.setCreditCardId(creditCardRequestDto.getCreditCardId());
					creditCard.setLineAvailable(creditCardRequestDto.getLineAvailable());
					creditCard.setFeeMonth(creditCardRequestDto.getFeeMonth());
					creditCard.setMinimumAmount(creditCardRequestDto.getMinimumAmount());
					creditCard.setSettlementAmount(creditCardRequestDto.getSettlementAmount());
					creditCard.setPayDay(creditCardRequestDto.getPayDay());
					creditCard.setCreateDate(creditCardRequestDto.getCreateDate());
					return Maybe.just(creditCardRepository.save(creditCard));
				})
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::creditCardToResponse)
				.toMaybe();
	}

	/**
	 * Paga el saldo de una tarjeta de credito dentro del repositorio segun los datos enviados en el body.
	 * @param creditCardPayRequestDto
	 * @return
	 */
	@Override
	public Maybe<CreditCardPayResponseDto> payCreditCard(CreditCardPayRequestDto creditCardPayRequestDto) {
		return Maybe.just(creditCardRepository.findByCreditCardId(creditCardPayRequestDto.getId()))
				.flatMap(uCreditCard -> {
					CreditCard creditCard = new CreditCard();
					creditCard.setId(creditCardPayRequestDto.getId());
					creditCard.setCustomerId(creditCardPayRequestDto.getCustomerId());
					creditCard.setCreditLine(uCreditCard.get(0).getCreditLine());
					creditCard.setCreditCardAccount(uCreditCard.get(0).getCreditCardAccount());
					creditCard.setCreditCardId(uCreditCard.get(0).getCreditCardId());
					creditCard.setLineAvailable(uCreditCard.get(0).getLineAvailable()+creditCardPayRequestDto.getAmount());
					creditCard.setFeeMonth(uCreditCard.get(0).getFeeMonth());
					creditCard.setMinimumAmount(uCreditCard.get(0).getMinimumAmount());
					creditCard.setSettlementAmount(uCreditCard.get(0).getSettlementAmount()-creditCardPayRequestDto.getAmount());
					creditCard.setPayDay(uCreditCard.get(0).getPayDay());
					creditCard.setCreateDate(uCreditCard.get(0).getCreateDate());
					return Maybe.just(creditCardRepository.save(creditCard));
				})
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::creditCardPayToResponse)
				.toMaybe();
	}

	/**
	 * Consume saldo de una tarjeta de credito dentro del repositorio segun los datos enviados en el body.
	 * @param creditCardConsumeRequestDto
	 * @return
	 */
	@Override
	public Maybe<CreditCardConsumeResponseDto> consumeCreditCard(CreditCardConsumeRequestDto creditCardConsumeRequestDto) {
		return Maybe.just(creditCardRepository.findByCreditCardId(creditCardConsumeRequestDto.getId()))
				.flatMap(uCreditCard -> {
					CreditCard creditCard = new CreditCard();
					creditCard.setId(creditCardConsumeRequestDto.getId());
					creditCard.setCustomerId(creditCardConsumeRequestDto.getCustomerId());
					creditCard.setCreditLine(uCreditCard.get(0).getCreditLine());
					creditCard.setCreditCardAccount(uCreditCard.get(0).getCreditCardAccount());
					creditCard.setCreditCardId(uCreditCard.get(0).getCreditCardId());
					creditCard.setLineAvailable(uCreditCard.get(0).getLineAvailable()-creditCardConsumeRequestDto.getAmount());
					creditCard.setFeeMonth(uCreditCard.get(0).getFeeMonth());
					creditCard.setMinimumAmount(uCreditCard.get(0).getMinimumAmount());
					creditCard.setSettlementAmount(uCreditCard.get(0).getSettlementAmount()+creditCardConsumeRequestDto.getAmount());
					creditCard.setPayDay(uCreditCard.get(0).getPayDay());
					creditCard.setCreateDate(uCreditCard.get(0).getCreateDate());
					return Maybe.just(creditCardRepository.save(creditCard));
				})
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::creditCardConsumeToResponse)
				.toMaybe();
	}
}