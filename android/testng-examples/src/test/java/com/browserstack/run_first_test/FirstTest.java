package com.browserstack.run_first_test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;

public class FirstTest extends BrowserStackTestNGTest {

	String usernameID = "test-Username";
    String passwordID = "test-Password";
    String submitButtonID = "test-LOGIN";
    By ProductTitle = By.xpath("//android.widget.TextView[@text='PRODUCTS']");

    @Test
    public void test() throws Exception {
    	System.out.println("Sauce - Start loginToSwagLabsTestValid test");
        login("standard_user", "secret_sauce");

        // Verification
        Assert.assertTrue(isOnProductsPage());
        
        
    }
    
    public void login(String user, String pass){

        WebDriverWait wait = new WebDriverWait(driver, 5);
        final WebElement usernameEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId(usernameID)));

            usernameEdit.click();
            usernameEdit.sendKeys(user);

        WebElement passwordEdit = driver.findElementByAccessibilityId(passwordID);
            passwordEdit.click();
            passwordEdit.sendKeys(pass);

        WebElement submitButton = driver.findElementByAccessibilityId(submitButtonID);
            submitButton.click();
    }
    
    public boolean isOnProductsPage() {
        //Create an instance of a Appium explicit wait so that we can dynamically wait for an element
        WebDriverWait wait = new WebDriverWait(driver, 5);

        //wait for the product field to be visible and store that element into a variable
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ProductTitle));
        } catch (TimeoutException e){
            System.out.println("*** Timed out waiting for product page to load.");
            return false;
        }
        return true;
    }
}
