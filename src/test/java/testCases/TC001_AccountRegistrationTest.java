package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccontRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		try {
			HomePage hp = new HomePage(driver);

			logger.info("Click on my account");
			hp.clickMyAccount();
			logger.info("Click on my Register");
			hp.clickRegister();

			AccontRegistrationPage reg = new AccontRegistrationPage(driver);

			logger.info("Providing customer details");
			reg.setFirstName(randomeString().toUpperCase());
			reg.setLastName(randomeString().toUpperCase());
			reg.setEmail(randomeString() + "@gmail.com");
			reg.setMobile(randomeNumber());

			String password = randomeAlphaNumeric();
			reg.setPassword(password);
			reg.setConfirmPassword(password);
			reg.setcheckSubscribe();
			reg.setcheckAgree();
			reg.setclckContinue();

			logger.info("Confirmed text message");
			String confmsg = reg.getConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("*** Finished TC001_AccountRegistrationTest ***");
	}
}