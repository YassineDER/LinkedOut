package org.ichat.backend;

import org.apache.tomcat.util.json.ParseException;
import org.ichat.backend.config.initUserData;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class initUserDataTest {

    @InjectMocks
    private initUserData initUserData;

    @Mock
    private UserRepo userRepo;

    @Mock
    private HttpResponse<String> mockResponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
    public void initData_shouldLogUser_whenApiResponseIsValid() throws IOException, InterruptedException, ParseException {
        // Given
        String validResponse = "{...}"; // Replace with a valid API response
        when(mockResponse.body()).thenReturn(validResponse);

        // When
        initUserData.initData(userRepo);

        // Then
        // Verify that LOGGER.info is called with the expected User object
    }

//    @Test
    public void parseUser_shouldReturnUser_whenJsonIsValid() throws ParseException {
        // Given
        String validJson = "{...}"; // Replace with a valid JSON string

        // When
        User result = initUserData.parseUser(validJson);
        User expectedUser = new User(); // Replace with the expected User object

        // Then
        assertEquals(expectedUser, result); // Replace expectedUser with the expected User object
    }

}