package IrfanChoudhury.SeleniumFrameWorkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IrfanChoudhury.AbstractComponents.Abstract_reusableComponents;

public class productCatalogue extends Abstract_reusableComponents {
	By findBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".mb-3 button:last-of-type");
	By toastMsg=By.cssSelector("#toast-container");
	
	
	WebDriver driver;
	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
//	WebElement userEmail=driver.findElement(By.id("userEmail"));
//	List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductLists() {
		waitForElementToAppear(findBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod=products.stream().filter(product->product.findElement(By.tagName("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		return prod;

		
	}
	public void addtoCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		Thread.sleep(2000);
//		waitForElementToDisappear(spinner);
		
	
	}

	}
