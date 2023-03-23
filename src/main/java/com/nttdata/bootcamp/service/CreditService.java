package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.CreditPayRequestDto;
import com.nttdata.bootcamp.model.CreditPayResponseDto;
import com.nttdata.bootcamp.model.CreditRequestDto;
import com.nttdata.bootcamp.model.CreditResponseDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Clase interfaz de servicio para los creditos
 */
public interface CreditService {

    Flowable<CreditResponseDto> getAll();

    Maybe<CreditResponseDto> getCreditById(String creditId);

    Maybe<CreditResponseDto> createCredit(CreditRequestDto creditRequestDto);

    Maybe<CreditResponseDto> updateCredit(CreditRequestDto creditRequestDto);

    Maybe<CreditPayResponseDto> payCredit(CreditPayRequestDto creditPayRequestDto);

}
