package com.booking.hotel;

import java.io.IOException;
import java.util.List;

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
	public void login() throws IOException, InterruptedException {

		// Insert Login Credentials

		WebElement emailTxtBox = findLocatorById("email");
		elementSendKeys(emailTxtBox, getCellData("Sheet1", 1, 0));
		WebElement passwordTxtBox = findLocatorById("pass");
		elementSendKeys(passwordTxtBox, getCellData("Sheet1", 1, 1));

		WebElement loginBtn = findLocatorByXpath("//button[@value='login']");
		elementClick(loginBtn);

		WebElement accountName = findLocatorByXpath("//a[@data-testid='username']");
		String successMsg = elementGetText(accountName);
		System.out.println(successMsg);
	}

	// Search Hotels
	@Test(priority = 2)
	public void searchHotel() throws IOException, InterruptedException {
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
	}

	// Select Hotel
	@Test(priority = 3)
	private void selectHotel() {
		WebElement exploreHotels = findLocatorByClassName("explore-hotels");
		visibilityOfElement(exploreHotels);

		WebElement hotelText = findLocatorByXpath("//h5[contains(text(),'Select')]");
		visibilityOfElement(hotelText);
		String textHotel = elementGetText(hotelText);
		System.out.println(textHotel);

	}
	// Print all Hotel Names and Prizes

	@Test(priority = 4)
	public void hotelNameAndPrizes() {
		List<WebElement> elementsByXpath = findElementsByXpath("//div[@id='hotellist']//h5");

		for (WebElement x : elementsByXpath) {

			String allHotels = elementGetText(x);
			System.out.println(allHotels);
		}

		List<WebElement> allHotelPrizes = findElementsByXpath("//div[@id='hotellist']//strong");
		for (WebElement prizes : allHotelPrizes) {
			String hotelPrize = elementGetText(prizes);
			System.out.println(hotelPrize);
		}

		// Select 3rd Hotel
		List<WebElement> continueBuuttons = findElementsByXpath("//div[@id='hotellist']//a");
		WebElement continueBtn = continueBuuttons.get(2);
		elementClick(continueBtn);
		clickOkAlert();
	}

	@Test(priority = 5)
	public void hotelNameText() {
		WebElement bookHotel = findLocatorByXpath("//h2[contains(text(),'Book Hotel')]");
		String bookingHotelName = elementGetText(bookHotel);
		System.out.println(bookingHotelName);
	}

	// Guest Details

	@Test(priority = 6)
	public void guestDetail() throws IOException {
		WebElement bookingFor = findLocatorById("own");
		elementClickJs(bookingFor);

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
	}

	// GST Details
	@Test(priority = 7)
	public void gstDeatils() throws IOException {
		WebElement gstRegNo = findLocatorById("gst_registration");
		elementSendKeys(gstRegNo, getCellData("Sheet1", 3, 0));

		WebElement gstCompany = findLocatorById("company_name");
		elementSendKeys(gstCompany, getCellData("Sheet1", 3, 1));

		WebElement gstCompanyAddress = findLocatorById("company_address");
		elementSendKeys(gstCompanyAddress, getCellData("Sheet1", 3, 2));

		WebElement step1Next = findLocatorById("step1next");
		elementClick(step1Next);
	}

	// special Request

	@Test(priority = 8)
	public void specialReq() throws IOException {
		WebElement highFloor = findLocatorById("high");
		elementClick(highFloor);

		WebElement otherRequest = findLocatorById("other_request");
		elementSendKeys(otherRequest, getCellData("Sheet1", 3, 3));

		WebElement step2Next = findLocatorById("step2next");
		elementClick(step2Next);
	}

	// Payment Details

	@Test(priority = 9)
	public void payment() throws IOException {
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
	}

	// Print OrderID

	@Test(priority = 10)
	public void orderId() throws IOException, InterruptedException {
		WebElement orderDeatils = findLocatorByClassName("successful");
		visibilityOfElement(orderDeatils);

		WebElement orderId = findLocatorByXpath("//strong[contains(text(),'#')]");
		String orderIdText = elementGetText(orderId);
		String replaceFirst = orderIdText.substring(1, 11);
		createCellAndSetCellData("Sheet1", 3, 12, replaceFirst);
		System.out.println(replaceFirst);

		WebElement BookingConfirm = findLocatorByXpath("//h2[contains(text(),'Booking')]");
		String confirmText = elementGetText(BookingConfirm);
		String[] split = confirmText.split(" ", 2);
		for (String text : split) {
			if (text.contains("Booking")) {
				System.out.println(text);
			}
		}
		//System.out.println(confirmText);
		
		WebElement bookedHotelName = findLocatorByXpath("//p[contains(text(),'Hotel ')]//strong");
		String bookedHotelText = elementGetText(bookedHotelName);
		System.out.println(bookedHotelText);

		// My Booking  Button
		WebElement myBookingBtn = findLocatorByXpath("//button[text()='My Booking']");
		elementClick(myBookingBtn);

		WebElement orderIdSearch = findLocatorByName("search");
		elementSendKeysEnter(orderIdSearch, getCellData("Sheet1", 3, 12));

		// Print OrderId
		Thread.sleep(2000);
		WebElement roomCode = findLocatorByClassName("room-code");
		String roomCodeText = elementGetText(roomCode);
		System.out.println(roomCodeText);

		WebElement roomHotelName = findLocatorByXpath("//div[@id='bookinglist']//h5");
		String roomHotelNameText = elementGetText(roomHotelName);
		System.out.println(roomHotelNameText);
		
		Thread.sleep(2000);
		WebElement totalPrize = findLocatorByClassName("total-prize");
		String prize = elementGetText(totalPrize);
		System.out.println(prize);
	}
	// Change Booking

	@Test(priority = 12)
	public void editBooking() throws IOException {
		WebElement editButton = findLocatorByXpath("//button[text()='Edit']");
		elementClickJs(editButton);

		WebElement checkInDate = findLocatorByName("check_in");
		elementSendKeysJs(checkInDate, getCellData("Sheet1", 1, 19));
		WebElement confirmBtn = findLocatorByXpath("//button[text()='Confirm']");
		elementClick(confirmBtn);

		WebElement successMsgText = findLocatorByClassName("alertMsg");
		String updateSuccessMsg = elementGetText(successMsgText);
		System.out.println(updateSuccessMsg);
	}

	// Cancel the Hotel
	@Test(priority = 13)
	private void cancelBooking() throws InterruptedException, IOException {
		WebElement myBookingTab = findLocatorById("step2");
		elementClick(myBookingTab);

		WebElement searchForCancel = findLocatorByName("search");
		elementSendKeysEnter(searchForCancel, getCellData("Sheet1", 3, 12));

		Thread.sleep(2000);
		WebElement orderIdForCancel = findLocatorByClassName("room-code");
		String orderIdForCancelText = elementGetText(orderIdForCancel);
		System.out.println(orderIdForCancelText);

		WebElement HotelNameForCancel = findLocatorByXpath("//div[@id='bookinglist']//h5");
		String hotelNameForCancelText = elementGetText(HotelNameForCancel);
		System.out.println(hotelNameForCancelText);

		WebElement totalPrizeConfirmationForCancel = findLocatorByClassName("total-prize");
		String totalPrizeConfirmationForCancelText = elementGetText(totalPrizeConfirmationForCancel);
		System.out.println(totalPrizeConfirmationForCancelText);

		WebElement cancelHotel = findLocatorByXpath("//a[text()='Cancel']");
		elementClick(cancelHotel);

		clickOkAlert();

		WebElement cancelMessage = findLocatorByXpath("//li[contains(text(),'cancelled')]");
		String cancelsuccessMsg = elementGetText(cancelMessage);
		System.out.println(cancelsuccessMsg);
	}

}
