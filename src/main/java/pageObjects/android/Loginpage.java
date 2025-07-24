package pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class Loginpage extends AndroidActions {

	AndroidDriver driver;

	public Loginpage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Sign In")
	private WebElement signIn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@hint='yourname@example.com']")
	private WebElement emailTextField;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@hint='Enter password']")
	private WebElement passwordTextField;
	
	@AndroidFindBy(accessibility = "Sign in")
	private WebElement signinButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
	private WebElement interswitchLogo;

	// Actions
	public void clickSignInButton() {
		signIn.click();
	}

	public void enterEmailAddress(String email) {
		emailTextField.click();
		emailTextField.sendKeys(email);
	}
	

	public void enterPassword(String password) {
		passwordTextField.click();
		passwordTextField.sendKeys(password);
	}
	
	public void clickLoginSignIn() {
		signinButton.click();
	}

	public void testElementVisibility() {
		Assert.assertTrue(interswitchLogo.isDisplayed(), "Login is successful");
	}
	

}
