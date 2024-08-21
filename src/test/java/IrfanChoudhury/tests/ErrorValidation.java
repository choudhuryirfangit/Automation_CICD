package IrfanChoudhury.tests;

import java.io.IOException;
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
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import IrfanChoudhury.AbstractComponents.Abstract_reusableComponents;
import IrfanChoudhury.SeleniumFrameWorkDesign.CartPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.LandingPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.checkoutPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.confirmationPage;
import IrfanChoudhury.SeleniumFrameWorkDesign.productCatalogue;
import IrfanChoudhury.testComponents.BaseTestDriverInvoke;
import dev.failsafe.Timeout;

public class ErrorValidation extends BaseTestDriverInvoke{
	
	@Test(groups= {"ErrorHandling"}) 
	public void loginErrMsg() throws IOException {
		// TODO Auto-generated method stub
		String productName="ADIDAS ORIGINAL";
		String countryName="India";

		landingPage.loginCredentials("irfan12@email.com", "Irfan@123");
		String loginErrMsg=landingPage.getErrorMessage();
		Assert.assertEquals(loginErrMsg, "Incorrect jmail or password.");
	
	}
	@Test
	public void productErrMsg() throws InterruptedException {
		String productName="ADIDAS ORIGINAL";
		String countryName="India";

//		LandingPage landingPage= launchApplication();

		productCatalogue productCatalogue=landingPage.loginCredentials("irfan123@email.com", "Irfan@123");
		
//		productCatalogue productCatalogue=new productC;ue(driver)
		List<WebElement> products= productCatalogue.getProductLists();
		productCatalogue.addtoCart(productName);
		
	
		CartPage cartPage=productCatalogue.goToCartPage();

		
//		CartPage cartPage=new CartPage(driver);
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
	}

}
