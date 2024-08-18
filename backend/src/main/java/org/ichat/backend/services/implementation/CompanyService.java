package org.ichat.backend.services.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.patchers.CompanyPatchDTO;
import org.ichat.backend.repository.CompanyRepo;
import org.ichat.backend.services.ICompanyService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new AccountException("Company not found by email", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Company findBy(Long company_id) {
        return companyRepo.findById(company_id)
                .orElseThrow(() -> new AccountException("Company not found by id", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Company update(Company oldCompany, CompanyPatchDTO newCompany) throws AccountException {
        if (newCompany.getName() != null)
            oldCompany.setCompany_name(newCompany.getName());
        if (newCompany.getDescription() != null)
            oldCompany.setDescription(newCompany.getDescription());
        if (newCompany.getSector() != null)
            oldCompany.setSector(newCompany.getSector());
        if (newCompany.getHeadquarters() != null)
            oldCompany.setHeadquarters(newCompany.getHeadquarters());
        if (newCompany.getWebsite() != null)
            oldCompany.setWebsite(newCompany.getWebsite());
        if (newCompany.getImage_url() != null)
            oldCompany.setImage_url(newCompany.getImage_url());

        return companyRepo.save(oldCompany);
    }

    @Override
    public Company create(Company company) {
        boolean exists = companyRepo.findByEmail(company.getEmail()).isPresent() ||
                companyRepo.findByUsername(company.getUsername()).isPresent();
        if (exists)
            throw new AccountException("Company already exists", HttpStatus.CONFLICT.value());

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
                    throw new AccountException("Error fetching company info", resp.getStatusCode().value());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, resp) -> {
                    throw new AccountException("Error fetching company info", resp.getStatusCode().value());
                })
                .body(String.class);

        JSONObject content = new JSONObject(response);
        String companyName = content.getString("simpleLabel");
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

    /**
     * Helper function to fetch the website of a company. It works by fetching the first result from google search and returning the display link. <br>
     * This is because the API used to fetch company info does not provide the website of the company.
     * @param companyName
     * @return the first result from google search as the website of the company
     *
     * @apiNote Sometimes, the website fetched may not be the correct one. This is because the company name may not be relevant to the company website.
     */
    private String fetchWebsiteOfCompany(String companyName) {
        String searchAPI = "https://www.googleapis.com/customsearch/v1?key={key}&cx={cx}&q={q}";

        String response = client.get()
                .uri(searchAPI, GOOGLE_API_KEY, GOOGLE_CX, companyName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, resp) -> {
                    throw new AccountException("Error fetching company website", resp.getStatusCode().value());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, resp) -> {
                    throw new AccountException("Error fetching company website", resp.getStatusCode().value());
                })
                .body(String.class);

        JSONObject content = new JSONObject(response);
        JSONObject first_result = (JSONObject) content.getJSONArray("items").get(0);
        return first_result.getString("displayLink");
    }

}
