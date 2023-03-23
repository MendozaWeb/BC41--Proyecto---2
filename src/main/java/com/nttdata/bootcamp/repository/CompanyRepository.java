package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para los métodos de acceso a la base de datos de los cliente empresariales
 */
@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    @Query("{'id':?0}")
    public List<Company> findByCompanyId(String id);
}
