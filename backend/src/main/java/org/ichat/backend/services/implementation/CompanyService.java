package org.ichat.backend.services.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.patchers.CompanyPatchDTO;
import org.ichat.backend.repository.account.CompanyRepository;
import org.ichat.backend.repository.account.UserRepository;
import org.ichat.backend.services.ICompanyService;
import org.ichat.backend.services.shared.IStorageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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
    private final CompanyRepository companyRepo;
    private final UserRepository userRepo;
    private final IStorageService storageService;
    private final RestClient client = RestClient.create();
    @Value("${google.api_key}")
    private String GOOGLE_API_KEY;
    @Value("${google.cx}")
    private String GOOGLE_CX;

    @Override
    public List<Company> findAll() {
        return companyRepo.findAll();
    }

    @Override
    public Company findBy(String email) {
        return companyRepo.findByEmail(email)
                .orElseThrow(() -> new AccountException("Company not found by given email", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Company findBy(Long company_id) {
        return companyRepo.findById(company_id)
                .orElseThrow(() -> new AccountException("Company not found by given id", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Company update(Long oldCompanyId, CompanyPatchDTO newCompany) throws AccountException {
        Company oldCompany = findBy(oldCompanyId);

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

        return companyRepo.save(oldCompany);
    }

    @Override
    public Company create(Company company) {
        boolean exists = userRepo.existsByEmail(company.getEmail()) || companyRepo.existsBySiren(company.getSiren())
                || userRepo.existsByUsername(company.getUsername());
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

        String imageName = companyName + "-" + company.getSiren() + ".png";
        String imagePath = "profile/images/" + imageName;
        company.setImageName(imagePath);
        storageService.uploadImageFromUrl(logoAPI + website, "user-assets", imagePath);

        return company;
    }

    /**
     * Helper function to fetch the website of a company. It works by fetching the first result from google search and returning the display link. <br>
     * This is because the API used to fetch company info does not provide the website of the company.
     *
     * @param companyName
     * @return the first result from google search as the website of the company
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
