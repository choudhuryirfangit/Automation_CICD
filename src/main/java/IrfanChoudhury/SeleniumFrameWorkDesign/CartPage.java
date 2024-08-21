package IrfanChoudhury.SeleniumFrameWorkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IrfanChoudhury.AbstractComponents.Abstract_reusableComponents;

public class CartPage extends Abstract_reusableComponents {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
//	List<WebElement> cartProducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match =cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	public checkoutPage checkout() { 
//		driver.findElement(By.cssSelector(".totalRow button")).click();
		checkOutBtn.click();
		return new checkoutPage(driver);
	}
	

}
