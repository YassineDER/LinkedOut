package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.util.patchers.CompanyPatch;

import java.util.List;

public interface ICompanyService {
    List<Company> findAll();
    Company findBy(String email);
    Company findBy(Long company_id);

    Company getCompanyBySIREN(String SIRET);

    Company update(Long oldUserID, CompanyPatch newCompany) throws AccountException;
    Company add(Company company);
}
