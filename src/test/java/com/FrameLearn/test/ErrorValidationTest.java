package com.FrameLearn.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FrameLearn.pages.CartPage;
import com.FrameLearn.pages.ProductCataloguePage;


public class ErrorValidationTest extends BaseTest {
	@Test(groups= {"Error Handling"},retryAnalyzer=Retry.class)
	public void incorrectLogin()
	{
		land.login("sugu2720@gmail.com", "hhjj");
		Assert.assertEquals(land.getErrorMessage(), "Incorrect ema or password.");
	}
	
	@Test(groups= {"Error Handling"})
	public void incorrectProduct()
	{
		String productName = "ZARA COAT 3";
		ProductCataloguePage productCatalogue=land.login("sugu2720@gmail.com", "Test@123");
		productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
		CartPage cartpage=productCatalogue.goToCartPage();		
		Boolean match = cartpage.matchTheProduct("Zara Coat235");
		Assert.assertFalse(match);

	}

}
