package pageObjects.android;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class RegistrationPage extends AndroidActions {

	AndroidDriver driver;

	public RegistrationPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Register")
	private WebElement registerButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
	private WebElement firstNameField;

	@AndroidFindBy(xpath = "//android.widget.EditText[@hint='Last Name']")
	private WebElement lastNameField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
	private WebElement emailField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(3)")
	private WebElement phoneNumberField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(4)")
	private WebElement passwordField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
	private WebElement OTPCodeField;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;

	// Actions
	public void clickRegistrationButton() {
		registerButton.click();
	}

	public void enterFirstName(String name) {
		firstNameField.click();
		firstNameField.sendKeys(name);
	}

	public void enterLastName(String name) {
		lastNameField.click();
		lastNameField.sendKeys(name);
	}

	public void enterEmail(String email) {
		emailField.click();
		emailField.sendKeys(email);
	}

	public void enterPhoneNumber(String number) {
		phoneNumberField.click();
		phoneNumberField.sendKeys(number);
	}

	public void enterPassword(String password) {
		passwordField.click();
		passwordField.sendKeys(password);
	}
	
//	public 

	public void enterOTPCode(String otpCode) {
		// Validate input
		if (otpCode == null || otpCode.length() != 6) {
			throw new IllegalArgumentException("OTP code must be exactly 6 digits");
		}

		// Loop through each digit and corresponding field
		for (int i = 0; i < 6; i++) {
			try {
				// Get the digit at position i
				String digit = String.valueOf(otpCode.charAt(i));

				// Create dynamic UiSelector for current instance
				String uiSelector = "new UiSelector().className(\"android.widget.EditText\").instance(" + i + ")";

				// Find the element dynamically
//                WebElement otpField = driver.findElement(By.xpath("//android.widget.EditText[@index='" + i + "']"));
//                 Alternative: 
				WebElement otpField = driver.findElement(AppiumBy.androidUIAutomator(uiSelector));

				// Clear and enter the digit
				otpField.click();
				otpField.sendKeys(digit);
//                otpField.sendKeys(digit);

				// Optional: Small delay between entries
				Thread.sleep(200);

			} catch (Exception e) {
				System.err.println("❌ Failed to enter digit at position " + i + ": " + e.getMessage());
				throw new RuntimeException("Failed to enter OTP digit at position " + (i + 1), e);
			}
		}
	}

	public void clickSubmitButton() {
		submitButton.click();
	}

}
