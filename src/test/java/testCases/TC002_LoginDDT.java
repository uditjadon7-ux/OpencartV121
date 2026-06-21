package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven")
	public void verify_loginDDT(String email, String password, String exp) {
		// home page

		logger.info("*****Starting TC_003_loginDDT *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// login page
			LoginPage lp = new LoginPage(driver);
			lp.emailaddress(email);
			lp.emailpassword(password);
			lp.clickLogin();

			// MyAccount
			MyAccountPage macc = new MyAccountPage(driver);

			boolean targetPage = macc.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {

					macc.clickLogOut();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("InValid")) {
				if (targetPage == true) {

					macc.clickLogOut();
					Assert.assertTrue(false);

				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*****Finished TC_003_loginDDT *****");
	}

}