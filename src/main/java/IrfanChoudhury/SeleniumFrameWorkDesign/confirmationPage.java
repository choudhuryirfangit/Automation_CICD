package IrfanChoudhury.SeleniumFrameWorkDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IrfanChoudhury.AbstractComponents.Abstract_reusableComponents;

public class confirmationPage extends Abstract_reusableComponents {
	
	WebDriver driver;
	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css=".hero-primary")
	WebElement finalMsg;
	
	public String confirmationMsg() {
		return finalMsg.getText();
	}

}
