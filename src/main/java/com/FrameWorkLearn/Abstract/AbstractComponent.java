package com.FrameWorkLearn.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.FrameLearn.pages.CartPage;
import com.FrameLearn.pages.OrderHistoryPage;

public class AbstractComponent {

	WebDriver driver ;
	WebDriverWait wait;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartIcon;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement MyOrdersIcon;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;	
		PageFactory.initElements(driver, this);
		 wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	
	public void waitForElementToAppear(By findBy)
	{
		
		
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement ele)
	{
		
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPage goToCartPage()
	{
		cartIcon.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
		
		
	}
	
public OrderHistoryPage goToMyOrderPage()
{
	MyOrdersIcon.click();
	
	OrderHistoryPage myOrderpage=new OrderHistoryPage(driver);
	return myOrderpage ;
	
}

	
	
}
