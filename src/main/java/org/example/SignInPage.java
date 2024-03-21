package org.example;

import dev.failsafe.internal.util.Assert;
import org.example.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage extends WebPage {
    private WebElement emailInputElement;
    private WebElement passwordInputElement;
    private WebElement loginButtonElement;
    private WebElement errorElement;

    public SignInPage(WebDriver driver) {
        super(driver);
        Assert.isTrue(getTitle().equals("Log in to Facebook"),
                "Wrong Page");
        emailInputElement = driver.findElement(By.id("email"));
        passwordInputElement = driver.findElement(By.id("pass"));
        loginButtonElement = driver.findElement(By.id("loginbutton"));

    }

    public WebElement getEmailInputElement() {
        waitForPageToLoad();
        return getDriver().findElement(By.id("email"));
    }

    public WebElement getPasswordInputElement() {
        waitForPageToLoad();
        return getDriver().findElement(By.id("pass"));
    }

    public WebElement getLoginButtonElement() {
        waitForPageToLoad();
        return getDriver().findElement(By.id("loginbutton"));
    }

    public WebElement getErrorElement(){
        waitForPageToLoad();
        return getDriver().findElement(By.id("aa"));
    }


    @Override
    protected String getUrl() {
        return "https://www.facebook.com/login/";
    }


    public LoginResult login(String email, String password) throws InterruptedException {
        waitForPageToLoad();

        getEmailInputElement().sendKeys(email);
        getPasswordInputElement().sendKeys(password);
        getLoginButtonElement().click();
        Thread.sleep(2000L);
        try {
            errorElement = getDriver().findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/form/div[1]/div[1]"));
            Assert.notNull(errorElement, "Error element is null");
            Assert.isTrue(errorElement.getText().contains(
                    "Wrong credentials"
            ), "Error");
            return LoginResult.Failed;
        } catch (Exception e) {
            return LoginResult.Success;
        }
    }

    public enum LoginResult {
        Failed,
        Success,
        FailedWithPassword,
        FailedWithUsername,
    }
}
