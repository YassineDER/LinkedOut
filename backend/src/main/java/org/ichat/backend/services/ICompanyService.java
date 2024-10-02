package org.ichat.backend.services;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.patchers.CompanyPatchDTO;

import java.util.List;

public interface ICompanyService {
    /**
     * Find all companies
     * @return List of companies
     */
    List<Company> findAll();

    /**
     * Find a company by its SIREN number (only in France). <br>
     * We use the API provided by the French government to get the company information, and Clearbit to get the favicon of a website.
     *
     * @param SIREN of the company
     * @return Company
     */
    Company getCompanyBySIREN(String SIREN);

    /**
     * Updates a company
     *
     * @param oldCompanyId
     * @param newCompany
     * @return The updated company
     * @throws AccountException if the company is not found
     */
    Company update(Long oldCompanyId, CompanyPatchDTO newCompany) throws AccountException;

    /**
     * Create a company
     * @param company
     * @return The created company
     */
    Company create(Company company);

}
