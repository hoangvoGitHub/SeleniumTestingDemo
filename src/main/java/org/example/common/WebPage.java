package org.example.common;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class WebPage {

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public WebPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofMillis(10000L));
        waitForPageToLoad();
        driver.get(getUrl());
    }
    protected abstract String getUrl();

    protected String getTitle(){
        return driver.getTitle();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }


    public void open(){
        driver.get(getUrl());
    }

    protected void waitForPageToLoad(){
        try {
            webDriverWait.ignoring(StaleElementReferenceException.class).until(webDriver ->
                    String.valueOf(
                    ((JavascriptExecutor) driver).executeScript(
                            "return document.readyState"
                    )
            ).equals("complete"));
        }catch (Throwable error){
            Assert.isTrue(false, error.getMessage());

        }
    }

    protected WebElement waitAndFindElement(By locator){
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
