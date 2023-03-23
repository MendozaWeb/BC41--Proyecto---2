package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para los métodos de acceso a la base de datos de los creditos
 */
@Repository
public interface CreditRepository extends MongoRepository<Credit, String> {
    @Query("{'id':?0}")
    public List<Credit> findByCreditId(String id);
}
