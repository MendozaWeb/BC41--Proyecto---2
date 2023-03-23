package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.AccountDepositRequestDto;
import com.nttdata.bootcamp.model.AccountDepositResponseDto;
import com.nttdata.bootcamp.model.AccountRequestDto;
import com.nttdata.bootcamp.model.AccountResponseDto;
import com.nttdata.bootcamp.model.AccountWithdrawRequestDto;
import com.nttdata.bootcamp.model.AccountWithdrawResponseDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Clase interfaz de servicio para las cuentas
 */
public interface AccountService {

	Flowable<AccountResponseDto> getAll();

	Maybe<AccountResponseDto> getAccountById(String accountId);

	Flowable<AccountResponseDto> getAccountByCustomerId(String customerId);

	Flowable<AccountResponseDto> getAccountByTypeAccount(String typeAccount);

	Maybe<AccountResponseDto> createAccountPersonal(AccountRequestDto accountRequestDto);

	Maybe<AccountResponseDto> updateAccount(AccountRequestDto accountRequestDto);

	Maybe<AccountDepositResponseDto> depositAccount(AccountDepositRequestDto accountDepositRequestDto);

	Maybe<AccountWithdrawResponseDto> withdrawAccount(AccountWithdrawRequestDto accountWithdrawRequestDto);

}
