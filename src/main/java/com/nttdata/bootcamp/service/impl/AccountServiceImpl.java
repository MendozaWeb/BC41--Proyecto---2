package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.Account;
import com.nttdata.bootcamp.model.AccountDepositRequestDto;
import com.nttdata.bootcamp.model.AccountDepositResponseDto;
import com.nttdata.bootcamp.model.AccountRequestDto;
import com.nttdata.bootcamp.model.AccountResponseDto;
import com.nttdata.bootcamp.model.AccountWithdrawRequestDto;
import com.nttdata.bootcamp.model.AccountWithdrawResponseDto;
import com.nttdata.bootcamp.model.Transaction;
import com.nttdata.bootcamp.repository.AccountRepository;
import com.nttdata.bootcamp.repository.CompanyRepository;
import com.nttdata.bootcamp.repository.PersonRepository;
import com.nttdata.bootcamp.repository.TransactionRepository;
import com.nttdata.bootcamp.service.AccountService;
import com.nttdata.bootcamp.util.Util;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de implementación para la interfaz AccountService
 */
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountRepository accountRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	/**
	 * Método que devuelve todas las cuentas dentro el repositorio.
	 * @return Flowable<AccountResponseDto>
	 */
	@Override
	public Flowable<AccountResponseDto> getAll() {
		return Flowable.just(accountRepository.findAll())
				.map(Util::accountToResponse);
	}

	/**
	 * Devuelve todas las cuentas dentro el repositorio segun el id del credito.
	 * @return Maybe<AccountResponseDto>
	 */
	@Override
	public Maybe<AccountResponseDto> getAccountById(String accountId) {
		return Flowable.just(accountRepository.findByAccountId(accountId))
				.map(Util::accountToResponse)
				.firstElement();
	}

	/**
	 * Devuelve todas las cuentas dentro el repositorio segun el id del cliente.
	 * @return Maybe<AccountResponseDto>
	 */
	@Override
	public Flowable<AccountResponseDto> getAccountByCustomerId(String customerId) {
		return Flowable.just(accountRepository.findAccountByCustomerId(customerId))
				.map(Util::accountToResponse);
	}

	/**
	 * Devuelve todas las cuentas dentro el repositorio segun el id del producto.
	 * @return Maybe<AccountResponseDto>
	 */
	@Override
	public Flowable<AccountResponseDto> getAccountByTypeAccount(String typeAccount) {
		return Flowable.just(accountRepository.findAccountByTypeAccount(typeAccount))
				.map(Util::accountToResponse);
	}

	/**
	 * Crea una nueva cuenta dentro del repositorio con los datos enviados en el body.
	 * @param accountRequestDto
	 * @return Flowable<AccountResponseDto>
	 */
	@Override
	public Maybe<AccountResponseDto> createAccountPersonal(AccountRequestDto accountRequestDto){
		Account account = new Account();
		account.setTypeAccount(accountRequestDto.getTypeAccount());
		account.setTypeCustomer(accountRequestDto.getTypeCustomer());
		account.setCustomerId(accountRequestDto.getCustomerId());
		account.setComMaintenance(accountRequestDto.getComMaintenance());
		account.setMovementDate(accountRequestDto.getMovementDate());
		account.setMonthlyMovements(accountRequestDto.getMonthlyMovements());
		account.setAmount(accountRequestDto.getAmount());
		account.setCreateDate(accountRequestDto.getCreateDate());
		account.setStatus(accountRequestDto.getStatus());
		return Maybe.just(personRepository.findByPersonId(accountRequestDto.getCustomerId()))
				.flatMap(a->{
					if(a.isEmpty()){
						return Maybe.empty();
					}
					return Maybe.just(accountRepository.findAccountByCustomerIdAndTypeAccount(a.stream().findFirst().get().getId()
									,accountRequestDto.getTypeAccount()))
							.flatMap(res ->{
								if(res.isEmpty()){
									return Maybe.just(Util.accountEmpty());
								}
								return Maybe.just(Util.accountExists());})
							.flatMap(res->{
								if(res.getStatus()==3){
									return Maybe.just(Util.accountExists());
								}
									return Maybe.just(accountRepository.save(account));});
				})
				.defaultIfEmpty(Util.accountEmpty())
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::accountToResponse)
				.toMaybe();
	}

	/**
	 * Crea una nueva cuenta dentro del repositorio con los datos enviados en el body.
	 * @param accountRequestDto
	 * @return Flowable<AccountResponseDto>
	 */
	@Override
	public Maybe<AccountResponseDto> createAccountCompany(AccountRequestDto accountRequestDto){
		Account account = new Account();
		account.setTypeAccount(accountRequestDto.getTypeAccount());
		account.setTypeCustomer(accountRequestDto.getTypeCustomer());
		account.setCustomerId(accountRequestDto.getCustomerId());
		account.setComMaintenance(accountRequestDto.getComMaintenance());
		account.setMovementDate(accountRequestDto.getMovementDate());
		account.setMonthlyMovements(accountRequestDto.getMonthlyMovements());
		account.setAmount(accountRequestDto.getAmount());
		account.setCreateDate(accountRequestDto.getCreateDate());
		account.setStatus(accountRequestDto.getStatus());
		return Maybe.just(companyRepository.findByCompanyId(accountRequestDto.getCustomerId()))
				.flatMap(c->{
					if(c.isEmpty()){
						return Maybe.empty();
					}
					if(accountRequestDto.getTypeAccount().equals("CUENTA_CORRIENTE")){
						return Maybe.just(accountRepository.save(account));
					}
					return Maybe.just(Util.typeNotCompany());
				}).defaultIfEmpty(Util.accountEmpty())
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::accountToResponse)
				.toMaybe();
	}

	/**
	 * Actualiza una cuenta dentro del repositorio segun los datos enviados en el body.
	 * @param accountRequestDto
	 * @return Maybe<AccountResponseDto>
	 */
	@Override
	public Maybe<AccountResponseDto> updateAccount(AccountRequestDto accountRequestDto) {
		return Maybe.just(accountRepository.findByAccountId(accountRequestDto.getId()))
				.flatMap(uAccount -> {
					Account account = new Account();
					account.setId(accountRequestDto.getId());
					account.setTypeAccount(accountRequestDto.getTypeAccount());
					account.setTypeCustomer(accountRequestDto.getTypeCustomer());
					account.setCustomerId(accountRequestDto.getCustomerId());
					account.setComMaintenance(accountRequestDto.getComMaintenance());
					account.setMovementDate(accountRequestDto.getMovementDate());
					account.setMonthlyMovements(accountRequestDto.getMonthlyMovements());
					account.setAmount(accountRequestDto.getAmount());
					account.setCreateDate(accountRequestDto.getCreateDate());
					account.setStatus(accountRequestDto.getStatus());
					return Maybe.just(accountRepository.save(account));
				})
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::accountToResponse)
				.toMaybe();
	}

	/**
	 * Deposita en una cuenta dentro del repositorio segun los datos enviados en el body.
	 * @param accountDepositRequestDto
	 * @return
	 */
	@Override
	public Maybe<AccountDepositResponseDto> depositAccount(AccountDepositRequestDto accountDepositRequestDto) {
		return Maybe.just(accountRepository.findByAccountId(accountDepositRequestDto.getId()))
				.flatMap(uAccount -> {
					Account account = new Account();
					account.setId(accountDepositRequestDto.getId());
					account.setTypeAccount(uAccount.get(0).getTypeAccount());
					account.setTypeCustomer(uAccount.get(0).getTypeCustomer());
					account.setCustomerId(uAccount.get(0).getCustomerId());
					account.setComMaintenance(uAccount.get(0).getComMaintenance());
					account.setMovementDate(uAccount.get(0).getMovementDate());
					account.setMonthlyMovements(uAccount.get(0).getMonthlyMovements());
					account.setAmount(uAccount.get(0).getAmount()+accountDepositRequestDto.getAmount());
					account.setCreateDate(uAccount.get(0).getCreateDate());
					account.setStatus(uAccount.get(0).getStatus());
					Transaction transaction = new Transaction();
					transaction.setProductType(accountDepositRequestDto.getTypeAccount());
					transaction.setProductId(accountDepositRequestDto.getId());
					transaction.setCustomerId(accountDepositRequestDto.getCustomerId());
					transaction.setTransactionType("DEPOSITO");
					transaction.setAmount(accountDepositRequestDto.getAmount());
					transaction.setTransactionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
					transactionRepository.save(transaction);
					return Maybe.just(accountRepository.save(account));
				})
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::accountDepositToResponse)
				.toMaybe();
	}

	/**
	 * Retira en una cuenta dentro del repositorio segun los datos enviados en el body.
	 * @param accountWithdrawRequestDto
	 * @return
	 */
	@Override
	public Maybe<AccountWithdrawResponseDto> withdrawAccount(AccountWithdrawRequestDto accountWithdrawRequestDto) {
		return Maybe.just(accountRepository.findByAccountId(accountWithdrawRequestDto.getId()))
				.flatMap(uAccount -> {
					Account account = new Account();
					account.setId(accountWithdrawRequestDto.getId());
					if(!uAccount.get(0).getCustomerId().equals(accountWithdrawRequestDto.getCustomerId())){
						return Maybe.just(Util.customerNotAccount());
					}
					account.setTypeAccount(uAccount.get(0).getTypeAccount());
					account.setTypeCustomer(uAccount.get(0).getTypeCustomer());
					account.setCustomerId(uAccount.get(0).getCustomerId());
					account.setComMaintenance(uAccount.get(0).getComMaintenance());
					account.setMovementDate(uAccount.get(0).getMovementDate());
					account.setMonthlyMovements(uAccount.get(0).getMonthlyMovements());
					if(uAccount.get(0).getAmount()>=accountWithdrawRequestDto.getAmount()){
						account.setAmount(uAccount.get(0).getAmount()-accountWithdrawRequestDto.getAmount());
					}
					else{
						return Maybe.just(Util.accountNotWithdraw());
					}
					account.setCreateDate(uAccount.get(0).getCreateDate());
					account.setStatus(uAccount.get(0).getStatus());
					Transaction transaction = new Transaction();
					transaction.setProductType(accountWithdrawRequestDto.getTypeAccount());
					transaction.setProductId(accountWithdrawRequestDto.getId());
					transaction.setCustomerId(accountWithdrawRequestDto.getCustomerId());
					transaction.setTransactionType("RETIRO");
					transaction.setAmount(accountWithdrawRequestDto.getAmount());
					transaction.setTransactionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
					transactionRepository.save(transaction);
					return Maybe.just(accountRepository.save(account));
				})
				.toFlowable()
				.collect(Collectors.toList())
				.map(Util::accountWithdrawToResponse)
				.toMaybe();
	}
}
