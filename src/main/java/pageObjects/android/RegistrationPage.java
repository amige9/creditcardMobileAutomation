package pageObjects.android;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	
	@AndroidFindBy(uiAutomator = "//android.widget.EditText[@hint='Enter password']")
	private WebElement passwordTextField;

//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
//	private WebElement interswitchLogo;

	// Actions
	public void clickRegistrationButton() {
		registerButton.click();
	}

	
	public void enterFirstName(String name) {
		firstNameField.click();
		firstNameField.sendKeys(name);
	}
	
//	public void enterEmailAddress(String email) {
//	    try {
//	        // Wait and find element
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        WebElement emailField = wait.until(ExpectedConditions.visibilityOf(emailTextField));
//	        
//	        // Clear and enter text
//	        emailField.clear();
//	        emailField.sendKeys(email);
//	        
//	        // Force validation
//	        String actualText = emailField.getAttribute("text");
//	        System.out.println("Expected: " + email + ", Actual: " + actualText);
//	        
//	        if (actualText == null || actualText.isEmpty()) {
//	            throw new RuntimeException("Email was not entered in the field!");
//	        }
//	        
//	    } catch (Exception e) {
//	        System.err.println("❌ Failed to enter email: " + e.getMessage());
//	        // Take screenshot for debugging
//	        // Add screenshot code here
//	        throw e;
//	    }
//	}

	public void enterPassword(String password) {
	    try {
	        // Wait and find element
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        WebElement passwordField = wait.until(ExpectedConditions.visibilityOf(passwordTextField));
	        
	        // Clear and enter text
	        passwordField.clear();
	        passwordField.sendKeys(password);
	        
	        // Force validation
	        String actualText = passwordField.getAttribute("text");
	        System.out.println("Expected: " + password+ ", Actual: " + actualText);
	        
	        if (actualText == null || actualText.isEmpty()) {
	            throw new RuntimeException("Email was not entered in the field!");
	        }
	        
	    } catch (Exception e) {
	        System.err.println("❌ Failed to enter email: " + e.getMessage());
	        // Take screenshot for debugging
	        // Add screenshot code here
	        throw e;
	    }
	}
//	public void enterPassword(String password) {
//		passwordTextField.sendKeys(password);
//	}

//	public void testElementVisibility() {
//		Assert.assertTrue(interswitchLogo.isDisplayed(), "Login is successful");
//
//	}

}
