package com.FrameLearn.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FrameWorkLearn.Abstract.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {

	WebDriver driver;
	
	public ProductCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toaster=By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	public List<WebElement> getProductList()
	
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	
	public WebElement selectProduct(String productName)
	{
		WebElement prod =	getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
	
	public void addToCart(String productName)
	{
		WebElement prod=selectProduct(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toaster);
		waitForElementToDisappear(spinner);

	}
	
	
}
