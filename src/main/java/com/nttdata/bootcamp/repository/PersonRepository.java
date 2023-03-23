package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para los métodos de acceso a la base de datos de los clientes personales
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
    @Query("{'id':?0}")
    public List<Person> findByPersonId(String id);
}
