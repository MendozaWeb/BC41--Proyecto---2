package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.Company;
import com.nttdata.bootcamp.model.CompanyRequestDto;
import com.nttdata.bootcamp.model.CompanyResponseDto;
import com.nttdata.bootcamp.repository.CompanyRepository;
import com.nttdata.bootcamp.service.CompanyService;
import com.nttdata.bootcamp.util.Util;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Clase de implementación para la interfaz CompanyService
 */
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

	/**
	 * Método que devuelve todos los clientes de tipo empresarial dentro el repositorio.
	 * @return Flowable<CompanyResponseDto>
	 */
	@Override
	public Flowable<CompanyResponseDto> getAll() {
		return Flowable.just(companyRepository.findAll())
				.map(Util::companyToResponse);
	}

	/**
	 * Devuelve todos los clientes de tipo empresarial dentro el repositorio segun el id del cliente.
	 * @return Maybe<CompanyResponseDto>
	 */
	@Override
	public Maybe<CompanyResponseDto> getCompanyById(String companyId) {
		return Flowable.just(companyRepository.findByCompanyId(companyId))
				.map(Util::companyToResponse)
				.firstElement();
	}

	/**
	 * Crea un cliente de tipo empresarial dentro del repositorio con los datos enviados en el body.
	 * @param companyRequestDto
	 * @return Maybe<CompanyResponseDto>
	 */
	@Override
	public Maybe<CompanyResponseDto> createCompany(CompanyRequestDto companyRequestDto) {
		Company company=new Company();
		company.setBusinessName(companyRequestDto.getBusinessName());
		company.setRuc(companyRequestDto.getRuc());
		company.setEmail(companyRequestDto.getEmail());
		company.setTelephone(companyRequestDto.getTelephone());
		company.setProfile("ESTANDAR");
		company.setHolders(companyRequestDto.getHolders());
		company.setSignatories(companyRequestDto.getSignatories());
		return Flowable.just(companyRepository.save(company))
				.collect(Collectors.toList())
				.map(Util::companyToResponse)
				.toMaybe();
	}

	/**
	 * Actualiza un cliente de tipo empresarial dentro del repositorio segun los datos enviados en el body.
	 * @param companyRequestDto
	 * @return Maybe<CompanyResponseDto>
	 */
	@Override
	public Maybe<CompanyResponseDto> updateCompany(CompanyRequestDto companyRequestDto) {
		Company company=new Company();
		company.setId(companyRequestDto.getId());
		company.setBusinessName(companyRequestDto.getBusinessName());
		company.setRuc(companyRequestDto.getRuc());
		company.setEmail(companyRequestDto.getEmail());
		company.setTelephone(companyRequestDto.getTelephone());
		company.setProfile(companyRepository.findByCompanyId(companyRequestDto.getId()).get(0).getProfile());
		company.setHolders(companyRequestDto.getHolders());
		company.setSignatories(companyRequestDto.getSignatories());
		return Flowable.just(companyRepository.save(company))
				.collect(Collectors.toList())
				.map(Util::companyToResponse)
				.toMaybe();
	}

}
