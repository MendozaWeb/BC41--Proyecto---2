package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.Person;
import com.nttdata.bootcamp.model.PersonRequestDto;
import com.nttdata.bootcamp.model.PersonResponseDto;
import com.nttdata.bootcamp.repository.PersonRepository;
import com.nttdata.bootcamp.service.AccountService;
import com.nttdata.bootcamp.service.PersonService;
import com.nttdata.bootcamp.util.Util;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

/**
 * Clase de implementación para la interfaz PersonService
 */
@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final AccountService accountService;

    /**
     * Método que devuelve todos los clientes de tipo personal dentro el repositorio.
     * @return Flowable<PersonResponseDto>
     */
    @Override
    public Flowable<PersonResponseDto> getAll() {
        return Flowable.just(personRepository.findAll())
                .map(Util::personToResponse);
    }

    /**
     * Devuelve todos los clientes de tipo personal dentro el repositorio segun el id del cliente.
     * @return Maybe<PersonResponseDto>
     */
    @Override
    public Maybe<PersonResponseDto> getPersonById(String personId) {
        return Flowable.just(personRepository.findByPersonId(personId))
                .map(Util::personToResponse)
                .firstElement();
    }

    /**
     * Crea un cliente de tipo personal dentro del repositorio con los datos enviados en el body.
     * @param personRequestDto
     * @return Maybe<PersonResponseDto>
     */
    @Override
    public Maybe<PersonResponseDto> createPerson(PersonRequestDto personRequestDto) {
        Person person = new Person();
        person.setName(personRequestDto.getName());
        person.setLastName(personRequestDto.getLastName());
        person.setDni(personRequestDto.getDni());
        person.setEmail(personRequestDto.getEmail());
        person.setTelephone(personRequestDto.getTelephone());
        person.setProfile("ESTANDAR");
        return Flowable.just(personRepository.save(person))
                .collect(Collectors.toList())
                .map(Util::personToResponse)
                .toMaybe();
    }

    /**
     * Actualiza un cliente de tipo empresarial dentro del repositorio segun los datos enviados en el body.
     * @param personRequestDto
     * @return Maybe<PersonResponseDto>
     */
    @Override
    public Maybe<PersonResponseDto> updatePerson(PersonRequestDto personRequestDto) {
        Person person = new Person();
        person.setId(personRequestDto.getId());
        person.setName(personRequestDto.getName());
        person.setLastName(personRequestDto.getLastName());
        person.setDni(personRequestDto.getDni());
        person.setEmail(personRequestDto.getEmail());
        person.setTelephone(personRequestDto.getTelephone());
        person.setProfile(personRepository.findByPersonId(personRequestDto.getId()).get(0).getProfile());
        return Flowable.just(personRepository.save(person))
                .collect(Collectors.toList())
                .map(Util::personToResponse)
                .toMaybe();
    }

}
