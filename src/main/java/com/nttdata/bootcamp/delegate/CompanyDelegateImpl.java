package com.nttdata.bootcamp.delegate;

import com.nttdata.bootcamp.api.CompanyApiDelegate;
import com.nttdata.bootcamp.model.CompanyRequestDto;
import com.nttdata.bootcamp.model.CompanyResponseDto;
import com.nttdata.bootcamp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyDelegateImpl implements CompanyApiDelegate {

    @Autowired
    private CompanyService companyService;

    /**
     * Create a new customer company in the bank
     * @param companyRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CompanyResponseDto> createCompany(CompanyRequestDto companyRequestDto) {
        return ResponseEntity.ok(companyService.createCompany(companyRequestDto).blockingGet());
    }

    /**
     * Find All Customers Company
     * @return
     */
    @Override
    public ResponseEntity<CompanyResponseDto> findAllCompany() {
        return ResponseEntity.ok(companyService.getAll().blockingFirst());
    }

    /**
     * Find Customers Company by id
     * @param companyId
     * @return
     */
    @Override
    public ResponseEntity<CompanyResponseDto> findCompanyById(String companyId) {
        return ResponseEntity.ok(companyService.getCompanyById(companyId).blockingGet());
    }

    /**
     * Update an existent customer company in the bank
     * @param companyRequestDto
     * @return
     */
    @Override
    public ResponseEntity<CompanyResponseDto> updateCompany(CompanyRequestDto companyRequestDto) {
        return ResponseEntity.ok(companyService.updateCompany(companyRequestDto).blockingGet());
    }
}
