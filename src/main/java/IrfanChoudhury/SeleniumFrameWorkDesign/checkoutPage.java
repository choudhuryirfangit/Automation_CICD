package IrfanChoudhury.SeleniumFrameWorkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IrfanChoudhury.AbstractComponents.Abstract_reusableComponents;

public class checkoutPage extends Abstract_reusableComponents{
	
	WebDriver driver;
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//*[@placeholder='Select Country']")
	WebElement checkOutBtn;
	
	@FindBy(css=".ta-item")
	List<WebElement> countryOptions;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".hero-primary")
	WebElement finalMsg;
	 
	By findBy=By.cssSelector(".ta-results");
	
	public void passCountryValue() {
		Actions a=new Actions(driver);
		a.sendKeys(checkOutBtn, "Ind").build().perform();
		waitForElementToAppear(findBy);
	}
	public void selectCountry(String countryName) {
		WebElement country=countryOptions.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		country.click();
		
	}
	public confirmationPage finalMsg() {
//		String finalMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		submit.click();
		return new confirmationPage(driver);
	}
	

}
