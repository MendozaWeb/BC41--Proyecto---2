package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.CreditCardConsumeRequestDto;
import com.nttdata.bootcamp.model.CreditCardConsumeResponseDto;
import com.nttdata.bootcamp.model.CreditCardPayRequestDto;
import com.nttdata.bootcamp.model.CreditCardPayResponseDto;
import com.nttdata.bootcamp.model.CreditCardRequestDto;
import com.nttdata.bootcamp.model.CreditCardResponseDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Clase interfaz de servicio para las tajetas de credito
 */
public interface CreditCardService {

	Flowable<CreditCardResponseDto> getAll();

	Maybe<CreditCardResponseDto> getCreditCardById(String creditCardId);

	Maybe<CreditCardResponseDto> createCreditCard(CreditCardRequestDto creditCardRequestDto);

	Maybe<CreditCardResponseDto> updateCreditCard(CreditCardRequestDto creditCardRequestDto);

	Maybe<CreditCardPayResponseDto> payCreditCard(CreditCardPayRequestDto creditCardPayRequestDto);

	Maybe<CreditCardConsumeResponseDto> consumeCreditCard(CreditCardConsumeRequestDto creditCardConsumeRequestDto);

}
