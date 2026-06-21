package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"sanity","Master"})
	public void verify_login() {
		logger.info("************* Starting TC_002_LoginTest ******");
		try {
			// home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// login page
			LoginPage lp = new LoginPage(driver);
			lp.emailaddress(p.getProperty("email"));
			lp.emailpassword(p.getProperty("password"));
			lp.clickLogin();

			// MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			macc.isMyAccountPageExists();
			boolean targetPage = macc.isMyAccountPageExists();

			// Assert.assertEquals(targetPage, true,"Login failed");
			Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail();
			logger.info("****** Finished TC_002_loginTest *******");
		}
	}
}
