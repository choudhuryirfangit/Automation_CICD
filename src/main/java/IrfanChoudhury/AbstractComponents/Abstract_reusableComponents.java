package IrfanChoudhury.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import IrfanChoudhury.SeleniumFrameWorkDesign.CartPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.OrderPage;

public class Abstract_reusableComponents {
	
	
	WebDriver driver;
	public Abstract_reusableComponents(WebDriver driver) {
		
		this.driver=driver;
	}
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement orderButton;
	

	public void waitForElementToAppear(By FindBy) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		
		
	}
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(ele));

	}
	public void waitForElementToAppear(WebElement FindBy) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(FindBy));
	}
	public CartPage goToCartPage() {
//		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		cartButton.click();
		
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage() {
		orderButton.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}

}
