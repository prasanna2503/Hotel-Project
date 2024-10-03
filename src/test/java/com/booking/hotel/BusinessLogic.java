package com.booking.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BusinessLogic extends BaseClass {

	@BeforeClass
	public void beforeClass() {
		browserLaunch();
		maximizeWindow();
		enterApplnUrl("https://www.omrbranch.com/");
		implicitWait();
	}

	@Test
	public void Ascending() throws IOException, InterruptedException {
		WebElement emailTxtBox = findLocatorById("email");
		elementSendKeys(emailTxtBox, getCellData("Sheet1", 1, 0));
		WebElement passwordTxtBox = findLocatorById("pass");
		elementSendKeys(passwordTxtBox, getCellData("Sheet1", 1, 1));

		WebElement loginBtn = findLocatorByXpath("//button[@value='login']");
		elementClick(loginBtn);

		WebElement hotelState = findLocatorById("state");
		selectOptionByText(hotelState, getCellData("Sheet1", 1, 2));
		Thread.sleep(2000);

		WebElement hotelCity = findLocatorById("city");
		selectOptionByText(hotelCity, getCellData("Sheet1", 1, 3));

		WebElement roomType = findLocatorById("room_type");
		// elementClick(roomType);
		selectOptionByText(roomType, getCellData("Sheet1", 1, 4));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 5));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 6));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 7));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 8));

		WebElement checkInDate = findLocatorByName("check_in");
		elementSendKeysJs(checkInDate, getCellData("Sheet1", 1, 17));
		WebElement checkOutDate = findLocatorByName("check_out");
		elementSendKeysJs(checkOutDate, getCellData("Sheet1", 1, 18));

		WebElement roomCount = findLocatorById("no_rooms");
		selectOptionByText(roomCount, getCellData("Sheet1", 1, 9));

		WebElement adultsCount = findLocatorById("no_adults");
		selectOptionByText(adultsCount, getCellData("Sheet1", 1, 10));

		WebElement childCount = findLocatorById("no_child");
		elementSendKeys(childCount, getCellData("Sheet1", 1, 11));

		WebElement frame = findLocatorByXpath("//iframe[@id='hotelsearch_iframe']");
		switchToFrameByWebElement(frame);

		WebElement searchButton = findLocatorById("searchBtn");
		elementClick(searchButton);

		defaultFrame();
		
		WebElement lowToHigh = findLocatorByXpath("//label[text()='Price low to high']");
		elementClick(lowToHigh);
		
		Thread.sleep(2000);
		List<WebElement> allHotelPrice = findElementsByXpath("//div[@id='hotellist']//strong");
		List<Integer> devPriceList = new ArrayList<>();
		List<Integer> testerPriceList = new ArrayList<>();
		for (WebElement x : allHotelPrice) {

			String allHotelsPrice = elementGetText(x);
			//System.out.println("All Price Lists:" + allHotelsPrice);
			String substring = allHotelsPrice.substring(1, 8);
			//System.out.println("Remove $ in All Price List:" + substring);
			String replace = substring.replace(" ", "");
			//System.out.println(replace);
			String replace2 = replace.replace(",", "");
			//System.out.println("Remove all Spaces in Price List:" + replace2);
			int priceList = Integer.parseInt(replace2);
			//System.out.println(priceList);
			devPriceList.add(priceList);
		}
			System.out.println(devPriceList);
			testerPriceList.addAll(devPriceList);
			Collections.sort(testerPriceList);
			System.out.println(testerPriceList);
			boolean b = devPriceList.equals(testerPriceList);
			if (b) {
				System.out.println("Correct Order");
			}else {
				System.out.println("NOT in Order");
			}
						
	}
	
	@Test
	public void hotelNameSort() throws InterruptedException {
		WebElement AscendingOrder = findLocatorByXpath("//label[text()='Name Ascending']");
		elementClick(AscendingOrder);
		Thread.sleep(2000);
		List<WebElement> allHotelNames = findElementsByXpath("//div[@id='hotellist']//h5");
		List<String> devHotelList = new ArrayList<>();
		List<String> testerHotelList = new ArrayList<>();
		for (WebElement eachHotel : allHotelNames) {
			String hotelNames = elementGetText(eachHotel);
			//System.out.println(hotelNames);
			String replace = hotelNames.replace(" ", "");
			//System.out.println(replace);
			String replace2 = replace.replace(" ", "");
			//System.out.println(replace2);
			String replace3 = replace2.replace(" ", "");
			//System.out.println(replace3);
			devHotelList.add(replace3);
		}
		System.out.println(devHotelList);
		testerHotelList.addAll(devHotelList);
		Collections.sort(testerHotelList);
		System.out.println(testerHotelList);
		boolean b = devHotelList.equals(testerHotelList);
		if (b) {
			System.out.println("Correct Order");
		}else {
			System.out.println("Not in Order");
		}
	}
}
