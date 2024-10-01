package com.booking.hotel;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingHotel extends BaseClass {
	
	@BeforeClass
	public void beforeClass() {
		browserLaunch();
		maximizeWindow();
		enterApplnUrl("https://www.omrbranch.com/");
		implicitWait();
	}
	
	@AfterClass(enabled = false)
	public void afterClass() {
		closeApplnWindow();
	}
	
	@Test
	public void bookingHotel() throws IOException, InterruptedException {
		
		//Insert Login Credentials
		
//		findLocatorById("email").sendKeys(getCellData("Sheet1", 1, 0));
//		findLocatorById("pass").sendKeys(getCellData("Sheet1", 1, 1));
		WebElement emailTxtBox = findLocatorById("email");
		elementSendKeys(emailTxtBox, getCellData("Sheet1", 1, 0));
		WebElement passwordTxtBox = findLocatorById("pass");
		elementSendKeys(passwordTxtBox, getCellData("Sheet1", 1, 1));
		
		WebElement loginBtn = findLocatorByXpath("//button[@value='login']");
		elementClick(loginBtn);
		
		WebElement accountName = findLocatorByXpath("//a[@data-testid='username']");
		String successMsg = elementGetText(accountName);
		System.out.println(successMsg);
		
		//Search Hotels
		
		WebElement hotelState = findLocatorById("state");
		selectOptionByText(hotelState, getCellData("Sheet1", 1, 2));
		Thread.sleep(2000);
		
		WebElement hotelCity = findLocatorById("city");
		selectOptionByText(hotelCity, getCellData("Sheet1", 1, 3));
		
		WebElement roomType = findLocatorById("room_type");
		//elementClick(roomType);
		selectOptionByText(roomType, getCellData("Sheet1", 1, 4));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 5));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 6));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 7));
		selectOptionByText(roomType, getCellData("Sheet1", 1, 8));
		
		WebElement checkInDate = findLocatorByName("check_in");
		elementClick(checkInDate);
		Actions action = new Actions(driver);
		WebElement selectCheckInDate = findLocatorByXpath("//a[text()='4']");
		mouseOverAction(selectCheckInDate);
		elementClick(selectCheckInDate);
		WebElement checkOutDate = findLocatorByName("check_out");
		elementClick(checkOutDate);
		WebElement selectCheckoutDate = findLocatorByXpath("//a[text()='6']");
		mouseOverAction(selectCheckoutDate);
		elementClick(selectCheckoutDate);
		
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
		//Select Hotel 
		WebElement exploreHotels = findLocatorByClassName("explore-hotels");
		visibilityOfElement(exploreHotels);
	
		WebElement hotelText = findLocatorByXpath("//h5[contains(text(),'Select')]");
		visibilityOfElement(hotelText);
		String textHotel = elementGetText(hotelText);
		System.out.println(textHotel);
		
		//Print all Hotel Names
		
		WebElement hotelName = findLocatorByClassName("hotel-suites");
		WebElement hotelNameText = hotelName.findElement(By.tagName("h5"));
		String allHotelName = elementGetText(hotelNameText);
		System.out.println(allHotelName);
		WebElement totalPrice = findLocatorByXpath("(//strong[text()='$ 3,068'])[1]");
		String hotelPrice = elementGetText(totalPrice);
		System.out.println(hotelPrice);
		
		// Select 3rd Hotel
		
		WebElement thirdHotel = findLocatorByXpath("(//a[text()='Continue'])[3]");
		elementClick(thirdHotel);
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		WebElement bookHotel = findLocatorByXpath("//h2[contains(text(),'Book Hotel')]");
		String bookingHotelName = elementGetText(bookHotel);
		System.out.println(bookingHotelName);
		
		//Guest Details
		
		WebElement bookingFor = findLocatorById("own");
		elementClick(bookingFor);
		
		WebElement userTitle = findLocatorById("user_title");
		elementSendKeys(userTitle, getCellData("Sheet1", 1, 12));
		
		WebElement guestFirstName = findLocatorById("first_name");
		elementSendKeys(guestFirstName, getCellData("Sheet1", 1, 13));
		
		WebElement guestLastName = findLocatorById("last_name");
		elementSendKeys(guestLastName, getCellData("Sheet1", 1, 14));
		
		WebElement guestPhoneNum = findLocatorById("user_phone");
		elementSendKeys(guestPhoneNum, getCellData("Sheet1", 1, 15));
		
		WebElement guestEmail = findLocatorById("user_email");
		elementSendKeys(guestEmail, getCellData("Sheet1", 1, 16));
		
		WebElement gstCheckBox = findLocatorById("gst");
		elementClick(gstCheckBox);
		
		//GST Details
		
		WebElement gstRegNo = findLocatorById("gst_registration");
		elementSendKeys(gstRegNo, getCellData("Sheet1", 3, 0));
		
		WebElement gstCompany = findLocatorById("company_name");
		elementSendKeys(gstCompany, getCellData("Sheet1", 3, 1));
		
		WebElement gstCompanyAddress = findLocatorById("company_address");
		elementSendKeys(gstCompanyAddress, getCellData("Sheet1", 3, 2));
		
		WebElement step1Next = findLocatorById("step1next");
		elementClick(step1Next);
		
		//special Request
		
		WebElement highFloor = findLocatorById("high");
		elementClick(highFloor);
		
		WebElement otherRequest = findLocatorById("other_request");
		elementSendKeys(otherRequest, getCellData("Sheet1", 3, 3));
		
		WebElement step2Next = findLocatorById("step2next");
		elementClick(step2Next);
		
		//Payment Details
		
		WebElement paymentDetails = findLocatorByClassName("credit-card");
		elementClick(paymentDetails);
		
		WebElement paymentType = findLocatorById("payment_type");
		selectOptionByText(paymentType, getCellData("Sheet1", 3, 4));
		
		WebElement cardType = findLocatorById("card_type");
		selectOptionByText(cardType, getCellData("Sheet1", 3, 5));
		
		WebElement cardNo = findLocatorById("card_no");
		elementSendKeys(cardNo, getCellData("Sheet1", 3, 6));
		
		WebElement nameOnCard = findLocatorById("card_name");
		elementSendKeys(nameOnCard, getCellData("Sheet1", 3, 7));
		
		WebElement cardMonth = findLocatorById("card_month");
		selectOptionByText(cardMonth, getCellData("Sheet1", 3, 8));
		
		WebElement cardYear = findLocatorById("card_year");
		selectOptionByText(cardYear, getCellData("Sheet1", 3, 9));
		
		WebElement cvv = findLocatorById("cvv");
		elementSendKeys(cvv, getCellData("Sheet1", 3, 10));
		
		WebElement submitButton = findLocatorById("submitBtn");
		elementClick(submitButton);
		
		//Print OrderID
		
		WebElement orderDeatils = findLocatorByClassName("successful");
		visibilityOfElement(orderDeatils);
		
		WebElement orderId = findLocatorByXpath("//strong[contains(text(),'#')]");
		String orderIdText = elementGetText(orderId);
		String replaceFirst = orderIdText.replaceFirst("^.", "");
		createCellAndSetCellData("Sheet1", 3, 11, replaceFirst);
		System.out.println(orderIdText);
		
		WebElement BookingConfirm = findLocatorByXpath("//h2[contains(text(),'Booking')]");
		String confirmText = elementGetText(BookingConfirm);
		System.out.println(confirmText);
		
		WebElement bookedHotelName = findLocatorByXpath("//strong[contains(text(),'Hyatt')]");
		String  bookedHotelText = elementGetText(bookedHotelName);
		System.out.println(bookedHotelText);
		
		//My Booking
		
		WebElement myBookingBtn = findLocatorByXpath("//button[text()='My Booking']");
		elementClick(myBookingBtn);
		
		WebElement orderIdSearch = findLocatorByName("search");
		elementSendKeysEnter(orderIdSearch, replaceFirst);
		
		//Print OrderId 
		WebElement roomCode = findLocatorByClassName("room-code");
		String roomCodeText = elementGetText(roomCode);
		System.out.println(roomCodeText);
		
		WebElement roomHotelName = findLocatorByXpath("//h5[contains(text(),'Hyatt Regency')]");
		String roomHotelNameText = elementGetText(roomHotelName);
		System.out.println(roomHotelNameText);
		
		WebElement totalPrize = findLocatorByClassName("total-prize");
		String prize = elementGetText(totalPrize);
		System.out.println(prize);
		
		WebElement editButton = findLocatorByXpath("//button[text()='Edit']");
		elementClick(editButton);
		
		WebElement updateCheckIn = findLocatorByName("check_in");
		elementClick(updateCheckIn);
		
		WebElement CheckInUpdate = findLocatorByXpath("//a[text()='11']");
		mouseOverAction(CheckInUpdate);
		elementClick(CheckInUpdate);
		
		WebElement confirmBtn = findLocatorByXpath("//button[text()='Confirm']");
		elementClick(confirmBtn);
		
		WebElement successMsgText = findLocatorByClassName("alertMsg");
		String updateSuccessMsg = elementGetText(successMsgText);
		System.out.println(updateSuccessMsg);
		
		
		
	}
	
}
