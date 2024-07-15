package com.FrameLearn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FrameWorkLearn.Abstract.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userName=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement Errorwindow;
	
	public ProductCataloguePage login(String Email,String pwd)
	{
		userName.sendKeys(Email);
		userPassword.sendKeys(pwd);
		login.click();
		ProductCataloguePage productCatalogue=new ProductCataloguePage(driver);
		return productCatalogue;
	}
	
	public void gotTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForElementToAppear(Errorwindow);
		String errorText=Errorwindow.getText();
		return errorText;
	}
	
	
		
	}

