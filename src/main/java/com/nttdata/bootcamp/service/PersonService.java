package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.PersonRequestDto;
import com.nttdata.bootcamp.model.PersonResponseDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Clase interfaz de servicio para las clientes personales
 */
public interface PersonService {

    Flowable<PersonResponseDto> getAll();

    Maybe<PersonResponseDto> getPersonById(String personId);

    Maybe<PersonResponseDto> createPerson(PersonRequestDto personRequestDto);

    Maybe<PersonResponseDto> updatePerson(PersonRequestDto personRequestDto);

}