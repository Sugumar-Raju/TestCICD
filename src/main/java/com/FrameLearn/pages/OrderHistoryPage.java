package com.FrameLearn.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
	
	WebDriver driver;
	public OrderHistoryPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List <WebElement> Ordersplaced;
	
	public Boolean VerifyTheProduct(String productName)
	{
		
		Boolean match = Ordersplaced.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

}
