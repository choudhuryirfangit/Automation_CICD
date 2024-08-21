package IrfanChoudhury.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import IrfanChoudhury.AbstractComponents.Abstract_reusableComponents;
import IrfanChoudhury.SeleniumFrameWorkDesign.CartPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.LandingPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.OrderPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.checkoutPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.confirmationPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.productCatalogue;
import IrfanChoudhury.testComponents.BaseTestDriverInvoke;
import dev.failsafe.Timeout;

public class PlcaeOrderTest extends BaseTestDriverInvoke{
	String productName="ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String countryName="India";

//		LandingPage landingPage= launchApplication();

		productCatalogue productCatalogue=landingPage.loginCredentials(input.get("email"), input.get("password"));
		
//		productCatalogue productCatalogue=new productC;ue(driver)
		List<WebElement> products= productCatalogue.getProductLists();
		productCatalogue.addtoCart(input.get("product"));
		
		CartPage cartPage=productCatalogue.goToCartPage();

		
//		CartPage cartPage=new CartPage(driver);
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		checkoutPage checkoutpage=cartPage.checkout();

//		checkoutPage checkoutpage=new checkoutPage(driver);
		checkoutpage.passCountryValue();
		checkoutpage.selectCountry(countryName);
		confirmationPage cP=checkoutpage.finalMsg();
//		confirmationPage cP=new confirmationPage(driver);
		String finalMessage=cP.confirmationMsg();

		Assert.assertTrue(finalMessage.equalsIgnoreCase("Thankyou for the order."));
//		Thread.sleep(2000);
//		driver.quit();

	} 
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		productCatalogue productCatalogue=landingPage.loginCredentials("irfan123@email.com", "Irfan@123");
		OrderPage orderpage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map=new HashMap<String, String>();
//		map.put("email", "irfan123@email.com");
//		map.put("password", "Irfan@123");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1=new HashMap<String, String>();
//		map1.put("email", "irfan99@email.com");
//		map1.put("password", "Irfan@321");
//		map1.put("product", "IPHONE 13 PRO");
		
		List<HashMap<String,String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\IrfanChoudhury\\Data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
//	public Object[][] getData() {
//		return new Object[][] {{"irfan123@email.com", "Irfan@123","ADIDAS ORIGINAL"},{"irfan99@email.com", "Irfan@321","IPHONE 13 PRO"}};
//	}
	
	

}
