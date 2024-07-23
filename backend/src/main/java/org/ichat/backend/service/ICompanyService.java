package org.ichat.backend.service;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.patchers.CompanyPatchDTO;

import java.util.List;

public interface ICompanyService {
    List<Company> findAll();
    Company findBy(String email);
    Company findBy(Long company_id);

    Company getCompanyBySIREN(String SIRET);

    Company update(Company oldCompany, CompanyPatchDTO newCompany) throws AccountException;
    Company add(Company company);

}
