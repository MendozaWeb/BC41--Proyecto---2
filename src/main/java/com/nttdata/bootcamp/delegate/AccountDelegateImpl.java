package com.nttdata.bootcamp.delegate;

import com.nttdata.bootcamp.api.AccountApiDelegate;
import com.nttdata.bootcamp.model.AccountDepositRequestDto;
import com.nttdata.bootcamp.model.AccountDepositResponseDto;
import com.nttdata.bootcamp.model.AccountRequestDto;
import com.nttdata.bootcamp.model.AccountResponseDto;
import com.nttdata.bootcamp.model.AccountWithdrawRequestDto;
import com.nttdata.bootcamp.model.AccountWithdrawResponseDto;
import com.nttdata.bootcamp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountDelegateImpl implements AccountApiDelegate {

    @Autowired
    private AccountService accountService;

    /**
     *  Create a new account company in the bank
     * @param accountRequestDto
     * @return
     */
    @Override
    public ResponseEntity<AccountResponseDto> createAccountCompany(AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.createAccountCompany(accountRequestDto).blockingGet());
    }

    /**
     * Create a new account person in the bank
     * @param accountRequestDto
     * @return
     */
    @Override
    public ResponseEntity<AccountResponseDto> createAccountPerson(AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.createAccountPersonal(accountRequestDto).blockingGet());
    }

    /**
     * Find Account by customerId
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<AccountResponseDto> findAccountByCustomerId(String customerId) {
        return ResponseEntity.ok(accountService.getAccountByCustomerId(customerId).blockingFirst());
    }

    /**
     * Find Account by type account
     * @param typeAccount
     * @return
     */
    @Override
    public ResponseEntity<AccountResponseDto> findAccountByTypeAccount(String typeAccount) {
        return ResponseEntity.ok(accountService.getAccountByTypeAccount(typeAccount).blockingFirst());
    }

    /**
     * Find Account by customerId
     * @param accountId
     * @return
     */
    @Override
    public ResponseEntity<AccountResponseDto> findAccountById(String accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId).blockingGet());
    }

    /**
     * Find All Account
     * @return
     */
    @Override
    public ResponseEntity<AccountResponseDto> findAllAccount() {
        return ResponseEntity.ok(accountService.getAll().blockingFirst());
    }

    /**
     * Update an existent Account in the bank
     * @param accountRequestDto
     * @return
     */
    @Override
    public ResponseEntity<AccountResponseDto> updateAccount(AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.updateAccount(accountRequestDto).blockingGet());
    }

    /**
     * Withdraw from account
     * @param accountWithdrawRequestDto
     * @return
     */
    @Override
    public ResponseEntity<AccountWithdrawResponseDto> withdrawAccount(AccountWithdrawRequestDto accountWithdrawRequestDto) {
        return ResponseEntity.ok(accountService.withdrawAccount(accountWithdrawRequestDto).blockingGet());
    }

    /**
     * Deposit into account
     * @param accountDepositRequestDto
     * @return
     */
    @Override
    public ResponseEntity<AccountDepositResponseDto> depositAccount(AccountDepositRequestDto accountDepositRequestDto) {
        return ResponseEntity.ok(accountService.depositAccount(accountDepositRequestDto).blockingGet());
    }


}
