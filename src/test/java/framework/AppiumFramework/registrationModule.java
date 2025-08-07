package framework.AppiumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;

import TestUtils.AndroidBaseTest;
import pageObjects.android.RegistrationPage;
import TestUtils.SharedTestData;

public class registrationModule extends AndroidBaseTest {

	@DataProvider
	public Object[][] getData() throws IOException {
		// Using Json format for test data
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "/src/test/java/testData/registrationData.json");
		return new Object[][] { { data.get(0) }};
	}


	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void loginTest(HashMap<String, String> input) throws InterruptedException, MailosaurException, IOException {
		
		// Initialize Page Objects
		RegistrationPage registrationPage = new RegistrationPage(driver);
		
        // Generate email and store in TestNG context
		String email = emailService.generateTestEmail("signup");
		String password = input.get("password");

		
        // Store credentials for Login module to use
//        SharedTestData.storeUserCredentials(email, password);
		
		// Step 1: Click Register Button
		registrationPage.clickRegistrationButton();
		
		
		// Step 2: Fill Registration Information
		registrationPage.enterFirstName(input.get("firstName"));
		registrationPage.enterLastName(input.get("lastName"));
		registrationPage.enterEmail(email);
		registrationPage.enterPhoneNumber(input.get("phoneNumber"));
		registrationPage.enterPassword(password);
		registrationPage.clickRegistrationButton();
				
		// Step 3: Enter OTP Code
		Message verificationEmail = emailService.waitForEmail(email);
        Assert.assertNotNull(verificationEmail, "Verification email not received");
		String verificationCode = emailService.getVerificationCode(verificationEmail);
		registrationPage.enterOTPCode(verificationCode);
		
		// Step 4: Click Submit Button
		registrationPage.clickSubmitButton();

        System.out.println("🎉 TEST PASSED - All steps completed successfully!");

	}
}
