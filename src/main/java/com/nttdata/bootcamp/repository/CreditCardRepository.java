package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para los métodos de acceso a la base de datos de las tarjetas de credito
 */
@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
    @Query("{'id':?0}")
    public List<CreditCard> findByCreditCardId(String id);
    @Query("{'customerId':?0}")
    public List<CreditCard> findCreditCardByCustomerId(String customerId);
}
