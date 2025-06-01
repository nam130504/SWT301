import LAB2.AccountService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {
    AccountService service = new AccountService();

    @ParameterizedTest(name = "Test {index} => username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 0)
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean result = service.registerAccount(username, password, email);
        assertEquals(expected, result, () -> "Test failed for: " + username + "/" + password + "/" + email);
    }
}
