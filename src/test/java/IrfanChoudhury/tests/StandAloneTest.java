package IrfanChoudhury.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dev.failsafe.Timeout;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName="ADIDAS ORIGINAL";
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("irfan123@email.com");
		driver.findElement(By.id("userPassword")).sendKeys("Irfan@123");
		driver.findElement(By.id("login")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		
//		for(WebElement product:products) {
//			String name=product.findElement(By.tagName("b")).getText();
//			
//			if(name.equalsIgnoreCase(productName)) {
//				product.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
//
//			}
			
//		}
//		Stream to replace long for loop
		WebElement prod=products.stream().filter(product->product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
		
//		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
//		for(WebElement cp:cartProducts) {
//			String pName=cp.getText();
//			if(pName.equalsIgnoreCase(productName)) {
//				Assert.assertTrue(true);
//				break;
//			}
//		}
//		Stream 
		Boolean match =cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//*[@placeholder='Select Country']")), "Ind").build().perform();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> countryOptions= driver.findElements(By.cssSelector(".ta-item"));
		for(WebElement cP:countryOptions) {
			if(cP.getText().equalsIgnoreCase("India")) {
				cP.click();
				break;
			}
		}
//		WebElement cp=countryOptions.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
//		cp.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String finalMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(finalMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
		
		
		

	}

}
