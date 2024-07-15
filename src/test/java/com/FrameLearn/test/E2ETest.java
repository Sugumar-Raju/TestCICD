package com.FrameLearn.test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FrameLearn.pages.CartPage;
import com.FrameLearn.pages.CheckOutPage;
import com.FrameLearn.pages.LandingPage;
import com.FrameLearn.pages.OrderHistoryPage;
import com.FrameLearn.pages.ProductCataloguePage;
public class E2ETest extends BaseTest {
@Test(dataProvider="getData",groups= {"Order Placement"})
	public void orderplacement(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		//String productName = "ZARA COAT 3";
		ProductCataloguePage productCatalogue=land.login(input.get("email"),input.get("pwd"));
		productCatalogue.getProductList();
		productCatalogue.addToCart(input.get("product"));
		CartPage cartpage=productCatalogue.goToCartPage();		
		Boolean match = cartpage.matchTheProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkout=cartpage.goTocheckoutPage();
	
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,500)");
	Thread.sleep(3000);
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
@Test(dependsOnMethods= {"orderplacement"})
public void VerifyOrderHistory()
{
	String productName = "ZARA COAT 3";
	ProductCataloguePage productCatalogue=land.login("sugu2720@gmail.com", "Test@123");
	OrderHistoryPage myOrderpage=productCatalogue.goToMyOrderPage();
	Boolean match =myOrderpage.VerifyTheProduct(productName);
	Assert.assertTrue(match);
	
}

/*@DataProvider
public Object[][] getData()
{
	return new Object[][] 
			{{"sugu2720@gmail.com", "Test@123","ZARA COAT 3"},{"Test_123@gmail.com","Test@123","ADIDAS ORIGINAL"}};
	
}
*/

/*@DataProvider
public Object[][] getData()
{
	
	HashMap <String, String>map= new HashMap<String, String>();
	map.put("email", "sugu2720@gmail.com");
	map.put("pwd", "Test@123");
	map.put("product", "ZARA COAT 3");
	HashMap <String, String>map1= new HashMap<String, String>();
	map1.put("email", "Test_123@gmail.com");
	map1.put("pwd", "Test@123");
	map1.put("product", "ADIDAS ORIGINAL");
	
	
	return new Object[][] {{map},{map1}};
	
}*/

@DataProvider
public Object[][] getData() throws IOException
{
	
	List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\com\\FrameLearn\\data\\ProductPurchase.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
	
}


}
