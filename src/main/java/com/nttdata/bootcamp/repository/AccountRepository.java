package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para los métodos de acceso a la base de datos de las cuentas
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{'id':?0}")
    public List<Account> findByAccountId(String id);
    @Query("{'customerId':?0}")
    public List<Account> findAccountByCustomerId(String customerId);
    @Query("{'typeAccount':?0}")
    public List<Account> findAccountByTypeAccount(String typeAccount);
    @Query("{'customerId':?0 ,'typeAccount':?1}")
    public List<Account> findAccountByCustomerIdAndTypeAccount(String customerId,String typeAccount);

}
