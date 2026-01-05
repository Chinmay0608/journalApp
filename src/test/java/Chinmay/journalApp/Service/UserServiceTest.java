package Chinmay.journalApp.Service;

import Chinmay.journalApp.entity.User;
import Chinmay.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach; // <-- Import this
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntryService userEntryService;

    // ✅ ADD THIS METHOD
    @BeforeEach
    public void setup() {
        // Delete the users this test is about to create
        // This ensures the test is repeatable
        userRepository.deleteByUserName("Ram");
        userRepository.deleteByUserName("Chinmay");
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Ram",
            "Shyam",
            "Chinmay",
            "Raj"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userEntryService.saveNewUser(user));
    }
}