package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By genderMale = By.xpath("//label[text()='Male']");
    private final By mobile = By.id("userNumber");
    private final By submitButton = By.id("submit");
    private final By successModal = By.id("example-modal-sizes-title-lg");

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(String fName, String lName, String emailStr, String mobileStr) {
        type(firstName, fName);
        type(lastName, lName);
        type(email, emailStr);
        click(genderMale);
        type(mobile, mobileStr);
    }

    public void submitForm() {
        scrollToElement(submitButton);
        click(submitButton);
    }

    public By getSuccessLocator() {
        return successModal;
    }

    public boolean isModalVisible() {
        try {
            return driver.findElement(successModal).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
