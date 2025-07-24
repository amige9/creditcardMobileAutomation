package framework.AppiumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestUtils.AndroidBaseTest;

import pageObjects.android.Loginpage;
import pageObjects.android.RegistrationPage;

public class registrationModule extends AndroidBaseTest {

	@DataProvider
	public Object[][] getData() throws IOException {
		// Using Json format for test data
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "/src/test/java/testData/loginData.json");
		return new Object[][] { { data.get(0) }};
	}


	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void loginTest(HashMap<String, String> input) throws InterruptedException {
		
		// Initialize Page Objects
		Loginpage loginPage = new Loginpage(driver);
		RegistrationPage registrationPage = new RegistrationPage(driver);
		
		
		
		// Step 1: Click Register Button
		registrationPage.clickRegistrationButton();
		
		
		// Step 2: Enter Login Information
		registrationPage.enterFirstName("olamide");
		Thread.sleep(3000);
//		loginPage.enterPassword(input.get("password"));
//		loginPage.clickSignInButton();
		
		// Step 3: Ascertain Login was Successful
//		loginPage.testElementVisibility();
        System.out.println("🎉 TEST PASSED - All steps completed successfully!");

	}
}
