package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.Credit;
import com.nttdata.bootcamp.model.CreditPayRequestDto;
import com.nttdata.bootcamp.model.CreditPayResponseDto;
import com.nttdata.bootcamp.model.CreditRequestDto;
import com.nttdata.bootcamp.model.CreditResponseDto;
import com.nttdata.bootcamp.model.Transaction;
import com.nttdata.bootcamp.repository.CreditRepository;
import com.nttdata.bootcamp.repository.TransactionRepository;
import com.nttdata.bootcamp.service.CreditService;
import com.nttdata.bootcamp.util.Util;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de implementación para la interfaz CreditService
 */
@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Método que devuelve todos los creditos dentro el repositorio.
     * @return Flowable<CreditResponseDto>
     */
    @Override
    public Flowable<CreditResponseDto> getAll() {
        return Flowable.just(creditRepository.findAll())
                .map(Util::creditToResponse);
    }

    /**
     * Devuelve todas los creditos dentro el repositorio segun el id del credito.
     * @return Maybe<CreditResponseDto>
     */
    @Override
    public Maybe<CreditResponseDto> getCreditById(String creditId) {
        return Flowable.just(creditRepository.findByCreditId(creditId))
                .map(Util::creditToResponse)
                .firstElement();
    }

    /**
     * Crea un nuevo credito dentro del repositorio con los datos enviados en el body.
     * @param creditRequestDto
     * @return Flowable<CreditResponseDto>
     */
    @Override
    public Maybe<CreditResponseDto> createCredit(CreditRequestDto creditRequestDto) {
        Credit credit = new Credit();
        credit.setCustomerId(creditRequestDto.getCustomerId());
        credit.setCustomerType(creditRequestDto.getCustomerType());
        credit.setTotalAmount(creditRequestDto.getTotalAmount());
        credit.setAmountFee(creditRequestDto.getAmountFee());
        credit.setQuantityFee(creditRequestDto.getQuantityFee());
        credit.setTotalFee(creditRequestDto.getTotalFee());
        credit.setSettlementAmount(creditRequestDto.getSettlementAmount());
        credit.setPayDay(creditRequestDto.getPayDay());
        credit.setCreateDate(creditRequestDto.getCreateDate());
        return Flowable.just(creditRepository.save(credit))
                .collect(Collectors.toList())
                .map(Util::creditToResponse)
                .toMaybe();
    }

    /**
     * Actualiza un credito dentro del repositorio segun los datos enviados en el body.
     * @param creditRequestDto
     * @return Maybe<CreditResponseDto>
     */
    @Override
    public Maybe<CreditResponseDto> updateCredit(CreditRequestDto creditRequestDto) {
        return Maybe.just(creditRepository.findByCreditId(creditRequestDto.getId()))
                .flatMap(uCredit -> {
                    Credit credit = new Credit();
                    credit.setId(creditRequestDto.getId());
                    credit.setCustomerId(creditRequestDto.getCustomerId());
                    credit.setCustomerType(creditRequestDto.getCustomerType());
                    credit.setTotalAmount(creditRequestDto.getTotalAmount());
                    credit.setAmountFee(creditRequestDto.getAmountFee());
                    credit.setQuantityFee(creditRequestDto.getQuantityFee());
                    credit.setTotalFee(creditRequestDto.getTotalFee());
                    credit.setSettlementAmount(creditRequestDto.getSettlementAmount());
                    credit.setPayDay(creditRequestDto.getPayDay());
                    credit.setCreateDate(creditRequestDto.getCreateDate());
                    return Maybe.just(creditRepository.save(credit));
                })
                .toFlowable()
                .collect(Collectors.toList())
                .map(Util::creditToResponse)
                .toMaybe();
    }

    /**
     * Paga el saldo de un credito dentro del repositorio segun los datos enviados en el body.
     * @param creditPayRequestDto
     * @return
     */
    @Override
    public Maybe<CreditPayResponseDto> payCredit(CreditPayRequestDto creditPayRequestDto) {
        return Maybe.just(creditRepository.findByCreditId(creditPayRequestDto.getId()))
                .flatMap(uCredit -> {
                    Credit credit = new Credit();
                    credit.setId(creditPayRequestDto.getId());
                    credit.setCustomerId(creditPayRequestDto.getCustomerId());
                    credit.setCustomerType(uCredit.get(0).getCustomerType());
                    credit.setTotalAmount(uCredit.get(0).getTotalAmount());
                    credit.setAmountFee(uCredit.get(0).getAmountFee());
                    credit.setQuantityFee(uCredit.get(0).getQuantityFee());
                    credit.setTotalFee(uCredit.get(0).getTotalFee());
                    credit.setSettlementAmount(uCredit.get(0).getSettlementAmount()-creditPayRequestDto.getAmount());
                    credit.setPayDay(uCredit.get(0).getPayDay());
                    credit.setCreateDate(uCredit.get(0).getCreateDate());
                    Transaction transaction = new Transaction();
                    transaction.setProductType("CREDITO");
                    transaction.setProductId(creditPayRequestDto.getId());
                    transaction.setCustomerId(creditPayRequestDto.getCustomerId());
                    transaction.setTransactionType("PAGO");
                    transaction.setAmount(creditPayRequestDto.getAmount());
                    transaction.setTransactionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    transactionRepository.save(transaction);
                    return Maybe.just(creditRepository.save(credit));
                })
                .toFlowable()
                .collect(Collectors.toList())
                .map(Util::creditPayToResponse)
                .toMaybe();
    }
}
