package com.nttdata.bootcamp.delegate;

import com.nttdata.bootcamp.api.PersonApiDelegate;
import com.nttdata.bootcamp.model.PersonRequestDto;
import com.nttdata.bootcamp.model.PersonResponseDto;
import com.nttdata.bootcamp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonDelegateImpl implements PersonApiDelegate {

    @Autowired
    private PersonService personService;

    /**
     * Create a new customer in the bank
     * @param personRequestDto
     * @return
     */
    @Override
    public ResponseEntity<PersonResponseDto> createPerson(PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.createPerson(personRequestDto).blockingGet());
    }

    /**
     * Find All Customers Personal
     * @return
     */
    @Override
    public ResponseEntity<PersonResponseDto> findAllPerson() {
        return ResponseEntity.ok(personService.getAll().blockingFirst());
    }

    /**
     * Find Customers Personal by id
     * @param personId
     * @return
     */
    @Override
    public ResponseEntity<PersonResponseDto> findPersonById(String personId) {
        return ResponseEntity.ok(personService.getPersonById(personId).blockingGet());
    }

    /**
     * Update an existent customer in the bank
     * @param personRequestDto
     * @return
     */
    @Override
    public ResponseEntity<PersonResponseDto> updatePerson(PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.updatePerson(personRequestDto).blockingGet());
    }
}
