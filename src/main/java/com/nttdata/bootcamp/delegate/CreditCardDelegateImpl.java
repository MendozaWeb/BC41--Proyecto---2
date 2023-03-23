package com.nttdata.bootcamp.delegate;

import com.nttdata.bootcamp.api.CreditCardApiDelegate;
import com.nttdata.bootcamp.model.CreditCardConsumeRequestDto;
import com.nttdata.bootcamp.model.CreditCardConsumeResponseDto;
import com.nttdata.bootcamp.model.CreditCardPayRequestDto;
import com.nttdata.bootcamp.model.CreditCardPayResponseDto;
import com.nttdata.bootcamp.model.CreditCardRequestDto;
import com.nttdata.bootcamp.model.CreditCardResponseDto;
import com.nttdata.bootcamp.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreditCardDelegateImpl implements CreditCardApiDelegate {

    @Autowired
    CreditCardService creditCardService;

    /**
     * Create a new CreditCard company in the bank
     * @param creditCardRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditCardResponseDto> createCreditCardCompany(CreditCardRequestDto creditCardRequestDto) {
        return ResponseEntity.ok(creditCardService.createCreditCard(creditCardRequestDto).blockingGet());
    }

    /**
     * Create a new CreditCard person in the bank
     * @param creditCardRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditCardResponseDto> createCreditCardPerson(CreditCardRequestDto creditCardRequestDto) {
        return ResponseEntity.ok(creditCardService.createCreditCard(creditCardRequestDto).blockingGet());
    }

    /**
     * Find All CreditCard
     * @return
     */
    @Override
    public ResponseEntity<CreditCardResponseDto> findAllCreditCard() {
        return ResponseEntity.ok(creditCardService.getAll().blockingFirst());
    }

    /**
     * Find CreditCard by customerId
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<CreditCardResponseDto> findCreditCardByCustomerId(String customerId) {
        return CreditCardApiDelegate.super.findCreditCardByCustomerId(customerId);
    }

    /**
     * Find CreditCard by id
     * @param creditCardId  (required)
     * @return
     */
    @Override
    public ResponseEntity<CreditCardResponseDto> findCreditCardById(String creditCardId) {
        return ResponseEntity.ok(creditCardService.getCreditCardById(creditCardId).blockingGet());
    }

    /**
     * Update an existent CreditCard in the bank
     * @param creditCardRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditCardResponseDto> updateCreditCard(CreditCardRequestDto creditCardRequestDto) {
        return ResponseEntity.ok(creditCardService.updateCreditCard(creditCardRequestDto).blockingGet());
    }

    /**
     * Consume credit card balance
     * @param creditCardConsumeRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditCardConsumeResponseDto> consumeCreditCard(CreditCardConsumeRequestDto creditCardConsumeRequestDto) {
        return ResponseEntity.ok(creditCardService.consumeCreditCard(creditCardConsumeRequestDto).blockingGet());
    }

    /**
     * Pay a CreditCard person or company in the bank
     * @param creditCardPayRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CreditCardPayResponseDto> payCreditCard(CreditCardPayRequestDto creditCardPayRequestDto) {
        return ResponseEntity.ok(creditCardService.payCreditCard(creditCardPayRequestDto).blockingGet());
    }
}
