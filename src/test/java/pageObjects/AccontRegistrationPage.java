package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccontRegistrationPage extends BasePage {

	WebDriver driver;

	public AccontRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement txtFirstName;
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement txtLastName;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtMobile;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	WebElement checkSubscribe;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkAgree;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement clckContinue;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);

	}

	public void setMobile(String tel) {
		txtMobile.sendKeys(tel);
	}

	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}

	public void setConfirmPassword(String confrimpass) {
		txtConfirmPassword.sendKeys(confrimpass);
	}

	public void setcheckSubscribe() {
		checkSubscribe.click();
	}

	public void setcheckAgree() {
		checkAgree.click();
	}

	public void setclckContinue() {
		clckContinue.click();
	}

	public String getConfirmationMsg()
	{ try {
		
	
		return(msgConfirmation.getText());
		
	}
	catch(Exception e)
	{
		return(e.getMessage());
	}
	}
}
