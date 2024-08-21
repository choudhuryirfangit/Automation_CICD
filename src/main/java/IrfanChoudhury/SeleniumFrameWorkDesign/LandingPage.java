package IrfanChoudhury.SeleniumFrameWorkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IrfanChoudhury.AbstractComponents.Abstract_reusableComponents;

public class LandingPage extends Abstract_reusableComponents {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
//	WebElement password=driver.findElement(By.id("userPassword"));
	@FindBy(id="userPassword")
	WebElement password;
	
//	WebElement submit=driver.findElement(By.id("login"));
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[class*='flyInOut']")
	WebElement errorMsg;
	
	public productCatalogue loginCredentials(String Email,String passkey) {
		userEmail.sendKeys(Email);
		password.sendKeys(passkey);
		submit.click();
		productCatalogue productCatalogue=new productCatalogue(driver);
		return productCatalogue;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMsg);
		String Errmsg=errorMsg.getText();
		return Errmsg;
	}


}
