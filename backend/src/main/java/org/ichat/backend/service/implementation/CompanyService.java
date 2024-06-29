package org.ichat.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.util.patchers.CompanyPatch;
import org.ichat.backend.repository.CompanyRepo;
import org.ichat.backend.service.ICompanyService;
import org.json.JSONObject;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {
    private final static String GOOGLE_API_KEY = System.getenv("GOOGLE_API_KEY");
    private final static String GOOGLE_CX = System.getenv("GOOGLE_CX");
    private final CompanyRepo companyRepo;
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
    public Company update(Long companyId, CompanyPatch newCompany) throws AccountException {
        Company companyToUpdate = findBy(companyId);

        if (newCompany.getName() != null)
            companyToUpdate.setCompany_name(newCompany.getName());
        if (newCompany.getDescription() != null)
            companyToUpdate.setDescription(newCompany.getDescription());
        if (newCompany.getSector() != null)
            companyToUpdate.setSector(newCompany.getSector());
        if (newCompany.getHeadquarters() != null)
            companyToUpdate.setHeadquarters(newCompany.getHeadquarters());
        if (newCompany.getWebsite() != null)
            companyToUpdate.setWebsite(newCompany.getWebsite());
        if (newCompany.getImage_url() != null)
            companyToUpdate.setImage_url(newCompany.getImage_url());

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
        String logoAPI = "https://logo.clearbit.com/";
        String url = "https://api.recherche-entreprises.fabrique.social.gouv.fr/api/v1/entreprise/" + SIREN;

        String response = client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, resp) -> {
                    throw new AccountException("Error fetching company info: " + resp.getStatusText());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, resp) -> {
                    throw new AccountException("Error fetching company info: " + resp.getStatusText());
                })
                .body(String.class);

        JSONObject content = new JSONObject(response);
        String companyName = content.getString("label");
        String sector = content.getString("activitePrincipale");
        String headquarters = content.getJSONObject("firstMatchingEtablissement").getString("address");
        String foundedDate = content.getString("dateDebut");
        String website = fetchWebsiteOfCompany(companyName);

        Company company = new Company();
        company.setCompany_name(companyName);
        company.setHeadquarters(headquarters);
        company.setFoundedDate(foundedDate);
        company.setWebsite(website);
        company.setSector(sector);
        company.setSiren(SIREN);
        company.setImage_url(logoAPI + website);

        return company;
    }


    private String fetchWebsiteOfCompany(String companyName) {
        String searchAPI = "https://www.googleapis.com/customsearch/v1?key={key}&cx={cx}&q={q}";

        String response = client.get()
                .uri(searchAPI, GOOGLE_API_KEY, GOOGLE_CX, companyName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, resp) -> {
                    throw new AccountException("Error fetching company website: " + resp.getStatusText());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, resp) -> {
                    throw new AccountException("Error fetching company website: " + resp.getStatusText());
                })
                .body(String.class);

        JSONObject content = new JSONObject(response);
        JSONObject first_result = (JSONObject) content.getJSONArray("items").get(0);
        return first_result.getString("displayLink");
    }

}
