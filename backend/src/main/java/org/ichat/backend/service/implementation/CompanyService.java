package org.ichat.backend.service.implementation;


import lombok.RequiredArgsConstructor;
import org.ichat.backend.config.ScheduledTasks;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Company;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.CompanyRepo;
import org.ichat.backend.service.ICompanyService;
import org.ichat.backend.service.IUserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {
    private final CompanyRepo companyRepo;
    private final IUserService userService;
    private final RestClient client = RestClient.create();

    @Override
    public List<Company> findAll() {
        return companyRepo.findAll();
    }

    @Override
    public Company findBy(String email) {
        return companyRepo.findByEmail(email)
                .orElseThrow(() -> new AccountException("Company not found by email"));
    }

    @Override
    public Company findBy(Long company_id) {
        return companyRepo.findById(company_id)
                .orElseThrow(() -> new AccountException("Company not found by id"));
    }

    @Override
    public void deleteBy(Long userID) {
        companyRepo.deleteById(userID);
    }

    @Override
    public Company update(Long oldUserID, Company newCompany) throws AccountException {
        Company companyToUpdate = (Company) userService.update(oldUserID, newCompany);

        companyToUpdate.setDescription(newCompany.getDescription());
        if (newCompany.getSector() != null)
            companyToUpdate.setSector(newCompany.getSector());
        if (newCompany.getHeadquarters() != null)
            companyToUpdate.setHeadquarters(newCompany.getHeadquarters());
        companyToUpdate.setAvailableJobs(newCompany.getAvailableJobs());

        return companyRepo.save(companyToUpdate);
    }

    @Override
    public Company add(Company company) {
        boolean exists = companyRepo.findByEmail(company.getEmail()).isPresent() ||
                companyRepo.findByUsername(company.getUsername()).isPresent();
        if (exists)
            throw new AccountException("Company already exists");

        return companyRepo.save(company);
    }

    @Override
    public Company getCompanyBySIREN(String SIREN) {
        if (!tokenIsValid()) {
            ApplicationContext context = new AnnotationConfigApplicationContext(ScheduledTasks.class);
            context.getBean(ScheduledTasks.class).setNewInpiToken();
        }

        String response = client.get()
                .uri("https://registre-national-entreprises.inpi.fr/api/companies/" + SIREN)
                .header("Authorization", "Bearer " + System.getProperty("INPI_TOKEN"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, resp) -> {
                    throw new AccountException("Error fetching company from INPI: " + resp.getStatusText());
                })
                .body(String.class);

        JSONObject content = new JSONObject(response).getJSONObject("formality").getJSONObject("content");
        JSONObject personneMorale = content.getJSONObject("personneMorale");
        JSONObject indentity = personneMorale.getJSONObject("identite");
        JSONArray activites = personneMorale.getJSONObject("etablissementPrincipal").getJSONArray("activites");
        JSONObject entreprise = indentity.getJSONObject("entreprise");

        String headquarters = personneMorale.getJSONObject("adresseEntreprise").getJSONObject("adresse")
                .getString("commune");
        int capital =  (int) indentity.getJSONObject("description").getFloat("montantCapital");
        LocalDate foundedDate = LocalDate.parse(content.getJSONObject("natureCreation").getString("dateCreation"));
        String companyName = entreprise.getString("denomination");
        StringBuilder sector = new StringBuilder();
        for (int i = 0; i < activites.length(); i++) {
            JSONObject activite = activites.getJSONObject(i);
            sector.append(activite.getString("activitePrincipale")).append(";");
        }

        Company company = new Company();
        company.setCompany_name(companyName);
        company.setCapital(capital);
        company.setHeadquarters(headquarters);
        company.setFoundedDate(foundedDate);
        company.setSector(sector.toString());

        return company;
    }

    private boolean tokenIsValid() {
        if (System.getProperty("INPI_TOKEN") == null)
            return false;

        AtomicBoolean isValid = new AtomicBoolean(true);
        client.get().uri("https://registre-national-entreprises.inpi.fr/api/companies/326094471")
                .header("Authorization", "Bearer " + System.getProperty("INPI_TOKEN"))
                .retrieve()
                .onStatus(status -> status.value() == 401, (req, resp) -> isValid.set(false));

        return isValid.get();
    }

}
