package Ebay;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class EbayTest extends GenericMethods {
	public static String url = "https://www.ebay.com/";
	public static String searchValue = "MacBook";

	@Test(priority = 0)
	public void applyMultipleFilters() throws InterruptedException {
		// opening browser
		openBrowser(url);

		// Navigating Search by category > Electronics > Cell Phones & accessories
		waitForElementVisible(createWebElementByXpath("//select[@aria-label='Select a category for search']"));

		click(createWebElementByXpath("//select[@aria-label='Select a category for search']"));

		selectFromList(createWebElementByXpath("//select[@aria-label='Select a category for search']"),
				"Cell Phones & Accessories");

		click(createWebElementByXpath("//input[@value='Search']"));

		// clicking on Cell Phones & Smartphones
		click(createWebElementByXpath("//a[.='Cell Phones & Smartphones']"));

		scrollToElement(createWebElementByXpath("//button[@aria-label='All Filters']"));

		// Clicking on All filters
		doubleClick(createWebElementByXpath("//button[@aria-label='All Filters']"));

		scrollToElement(createWebElementByXpath("(//span[.='Style'])[last()]"));

		// Applying THree Filters Condition, Price, Item Location
		doubleClick(createWebElementByXpath("(//span[.='Condition'])[last()]"));

		scrollToElement(createWebElementByXpath("//input[contains(@id,'New')]"));

		click(createWebElementByXpath("//input[contains(@id,'New')]"));

		doubleClick(createWebElementByXpath("(//span[.='Price'])[last()]"));

		setText(createWebElementByXpath("//input[contains(@aria-label,'Minimum Value')]"), "100");

		setText(createWebElementByXpath("//input[contains(@aria-label,'Maximum Value')]"), "150");

		doubleClick(createWebElementByXpath("(//span[.='Item Location'])[last()]"));

		click(createWebElementByXpath("(//span[.='US Only'])[1]//input"));

		click(createWebElementByXpath("//button[@aria-label='Apply']"));

		Thread.sleep(3000);

		waitForElementVisible(createWebElementByXpath("(//span[@class='brm__flyout__btn-label'])[1]"));

		String filterNumber = getText(createWebElementByXpath("(//span[@class='brm__flyout__btn-label'])[1]"));

		// Verifying filters are applied successfully
		Assert.assertEquals("3 filters applied", filterNumber);

		System.out.println(filterNumber);

		click(createWebElementByXpath("(//span[@class='brm__flyout__btn-label'])[1]"));

		// printing filter names whic are applied
		ArrayList<WebElement> filters = (ArrayList<WebElement>) driver
				.findElements(By.xpath("//li[@class='brm__aspect-item brm__aspect-item--applied']"));
		for (WebElement w : filters) {
			System.out.println(w.getText());
		}

		// Closing Browser
		closeBrowser();

	}

	@Test(priority = 1)
	public void accessProductViaSearch() throws InterruptedException {
		// opening browser
		openBrowser(url);

		// Searching for anything ex. MacBook
		waitForElementVisible(createWebElementByXpath("//input[@placeholder='Search for anything']"));

		setText(createWebElementByXpath("//input[@placeholder='Search for anything']"), searchValue);

		//Selecting Computers/Tablets & Networking from list and clicking on search
		click(createWebElementByXpath("//select[@aria-label='Select a category for search']"));

		selectFromList(createWebElementByXpath("//select[@aria-label='Select a category for search']"),
				"Computers/Tablets & Networking");

		click(createWebElementByXpath("//input[@value='Search']"));

		//waiting for 3 seconds to page load
		Thread.sleep(3000);

		//Verifying first result matches with searchValue
		String firstResult = getText(createWebElementByXpath("(//div[@class='s-item__title'])[3]"));

		if (firstResult.contains(searchValue)) {

			System.out.println("Result Matched with searchValue");

		} else {
			Assert.fail("Result Matched with searchValue");
		}
		
		//closing browser
		closeBrowser();
	}
}
