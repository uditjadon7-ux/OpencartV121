package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtEmailPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement clckLoginButton;

	public void emailaddress(String email) {
		txtEmailAddress.sendKeys(email);
	}

	public void emailpassword(String pass) {
		txtEmailPassword.sendKeys(pass);
	}

	public void clickLogin()
	{
		clckLoginButton.click();
	}
	
}
