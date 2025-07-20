package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("DemoQA Registration Form Tests")
public class RegistrationTest extends BaseTest {

    static RegistrationPage regPage;
    static WebDriverWait wait;

    @BeforeAll
    static void initPage() {
        regPage = new RegistrationPage(driver);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @ParameterizedTest(name = "Registration Test with CSV: {0} {1}, {2}, {3}")
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    @Order(1)
    @DisplayName("Registration Test using data from CSV file")
    void testRegistrationWithCSV(String fName, String lName, String email, String mobile, String expected) {
        regPage.navigate();

        fName = fName == null ? "" : fName.trim();
        lName = lName == null ? "" : lName.trim();
        email = email == null ? "" : email.trim();
        mobile = mobile == null ? "" : mobile.trim();

        regPage.fillForm(fName, lName, email, mobile);
        regPage.submitForm();

        if (expected.equalsIgnoreCase("success")) {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(regPage.getSuccessLocator()));
            assertTrue(modal.getText().contains("Thanks for submitting the form"));
        } else {
            assertFalse(regPage.isModalVisible());
        }
    }
}
