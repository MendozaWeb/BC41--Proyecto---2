package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para los métodos de acceso a la base de datos de las transacciones
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    @Query("{'id':?0}")
    public List<Transaction> findByTransactionId(String id);
    @Query("{'customerId':?0}")
    public List<Transaction> findTransactionByCustomerId(String customerId);
    @Query("{'productId':?0}")
    public List<Transaction> findTransactionByProductId(String productId);
}
