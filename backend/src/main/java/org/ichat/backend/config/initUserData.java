package org.ichat.backend.config;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.UserRepo;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Configuration
@Profile("dev")
public class initUserData {
    private final static Logger LOGGER = LoggerFactory.getLogger(initUserData.class);

//    @Bean
    public CommandLineRunner initData(UserRepo userRepo) {
        return args -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://randomuser.me/api/"))
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                User user = parseUser(response.body());
                LOGGER.info("Adding user: " + userRepo.save(user));
            } catch (IOException | InterruptedException | ParseException e) {
                LOGGER.error("Error occurred while fetching and parsing user data", e);
            }
        };
    }

    public User parseUser(String json) throws ParseException {
        JSONParser parser = new JSONParser(json);
        Map<String, Object> parsedJson = parser.parseObject();
        Map<String, Object> results = (Map<String, Object>) ((List) parsedJson.get("results")).get(0);
        Map<String, Object> name = (Map<String, Object>) results.get("name");
        Map<String, Object> picture = (Map<String, Object>) results.get("picture");
        Map<String, Object> location = (Map<String, Object>) results.get("location");
        Map<String, Object> street = (Map<String, Object>) location.get("street");

        User user = new User();
        user.setFirst_name((String) name.get("first"));
        user.setLast_name((String) name.get("last"));
        user.setEmail((String) results.get("email"));
        user.setPhone((String) results.get("phone"));
        user.setImage_url((String) picture.get("large"));
        user.setAddress(street.get("number") + " " + street.get("name"));

        return user;
    }
}