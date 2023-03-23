package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.CompanyRequestDto;
import com.nttdata.bootcamp.model.CompanyResponseDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Clase interfaz de servicio para las clientes empresariales
 */
public interface CompanyService {

	Flowable<CompanyResponseDto> getAll();

	Maybe<CompanyResponseDto> getCompanyById(String companyId);

	Maybe<CompanyResponseDto> createCompany(CompanyRequestDto companyRequestDto);

	Maybe<CompanyResponseDto> updateCompany(CompanyRequestDto companyRequestDto);

}
