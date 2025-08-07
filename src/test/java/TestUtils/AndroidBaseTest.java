package TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public ExtentReports extent;
	public EmailService emailService;

//	public BaseTest(AndroidDriver driver) 
//	{
//		super(driver);
//		this.driver = driver;
//	}



	@BeforeClass(alwaysRun = true)
	public void ConfigAppium() throws URISyntaxException, IOException {
		
        // Initialize EmailService once for the entire test class
        emailService = new EmailService();
        System.out.println("✅ EmailService initialized");

		Properties properties = new Properties();
		Path filePath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "resources",
				"data.properties");

//		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\resources\\data.properties");
//		properties.load(fileInputStream);
		FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
		properties.load(fileInputStream);

//		String ipAddress =  properties.getProperty("ipAddress");
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: properties.getProperty("ipAddress");
		String port = properties.getProperty("port");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));
//        
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(properties.getProperty("AndriodDeviceName"));
		options.setNoReset(false);
//		options.setFullReset(true);

		// Get user home directory
		String userHome = System.getProperty("user.home");
		String projectPath = System.getProperty("user.dir");
		String osName = System.getProperty("os.name").toLowerCase();

		// Add this capability for automatic ChromeDriver management
		// ChromeDriver path
		String chromeDriverName = osName.contains("win") ? "chromedriver.exe" : "chromedriver";
		String chromeDriverPath = projectPath + File.separator + "src" + File.separator + 
		                         "test" + File.separator + "java" + File.separator + 
		                         "drivers" + File.separator + chromeDriverName;
		options.setChromedriverExecutable(chromeDriverPath);

		// App path using relative path from project root
//		String projectPath = System.getProperty("user.dir");
		String appPath = projectPath + File.separator + "src" + File.separator + "test" + File.separator + "java"
				+ File.separator + "resources" + File.separator + "credit_app_1.0.0.39.apk";
		
		options.setApp(appPath);

//		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		AndroidDriver driver = new AndroidDriver(new URI("http://192.168.1.101:4723").toURL(), options);

	}

//	@BeforeMethod(alwaysRun = true)
//	public void preSetup() {
//		System.out.println("🔄 Resetting app state...");
//		driver.terminateApp("com.androidsample.generalstore");
//		driver.activateApp("com.androidsample.generalstore");
//	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			try {
				System.out.println("🛑 Cleaning up test session...");
				driver.quit();
				service.stop();
			} catch (Exception e) {
				System.err.println("Error quitting driver: " + e.getMessage());
			} finally {
				driver = null;
			}
		}
	}

}
