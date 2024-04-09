package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> findAll();
    Company findBy(String email);
    Company findBy(Long company_id);

    Company getCompanyBySIREN(String SIRET);

    void deleteBy(Long userID);
    Company update(Long oldUserID, Company newCompany) throws AccountException;
    Company add(Company company);
}
