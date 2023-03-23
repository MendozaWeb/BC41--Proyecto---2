package com.nttdata.bootcamp.delegate;

import com.nttdata.bootcamp.api.CreditApiDelegate;
import com.nttdata.bootcamp.model.CreditPayRequestDto;
import com.nttdata.bootcamp.model.CreditPayResponseDto;
import com.nttdata.bootcamp.model.CreditRequestDto;
import com.nttdata.bootcamp.model.CreditResponseDto;
import com.nttdata.bootcamp.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreditDelegateImpl implements CreditApiDelegate {

    @Autowired
    CreditService creditService;

    /**
     * Create a new Credit company in the bank
     * @param creditRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditResponseDto> createCreditCompany(CreditRequestDto creditRequestDto) {
        return ResponseEntity.ok(creditService.createCredit(creditRequestDto).blockingGet());
    }

    /**
     * Create a new Credit person in the bank
     * @param creditRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditResponseDto> createCreditPerson(CreditRequestDto creditRequestDto) {
        return ResponseEntity.ok(creditService.createCredit(creditRequestDto).blockingGet());
    }

    /**
     * Find All Credit
     * @return
     */
    @Override
    public ResponseEntity<CreditResponseDto> findAllCredit() {
        return ResponseEntity.ok(creditService.getAll().blockingFirst());
    }

    /**
     * Find Credit by id
     * @param creditId
     * @return
     */
    @Override
    public ResponseEntity<CreditResponseDto> findCreditById(String creditId) {
        return ResponseEntity.ok(creditService.getCreditById(creditId).blockingGet());
    }

    /**
     * Update an existent Credit in the bank
     * @param creditRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditResponseDto> updateCredit(CreditRequestDto creditRequestDto) {
        return ResponseEntity.ok(creditService.updateCredit(creditRequestDto).blockingGet());
    }

    /**
     * Pay a Credit person or company in the bank
     * @param creditPayRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditPayResponseDto> payCredit(CreditPayRequestDto creditPayRequestDto) {
        return ResponseEntity.ok(creditService.payCredit(creditPayRequestDto).blockingGet());
    }
}
