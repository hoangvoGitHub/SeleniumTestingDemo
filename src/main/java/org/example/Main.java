package org.example;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {


//        String BASE_URL = "https://www.facebook.com/login/";
//        FirefoxDriver driver = new FirefoxDriver();
//
//        // Method to open the browser
//        driver.get(BASE_URL);
//
//        driver.findElement(By.id("email")).sendKeys("username");
//        Thread.sleep(2000L);
//        driver.findElement(By.id("pass")).sendKeys("password");
//        Thread.sleep(2000L);
//        driver.findElement(By.id("loginbutton")).click();

        String node1Url = "http://192.168.0.114:6666";
        String node2Url = "http://192.168.0.114:4444";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        RemoteWebDriver driver = new RemoteWebDriver(new URL(node2Url), desiredCapabilities);
        SignInPage signInPage = new SignInPage(driver);
        signInPage.login("hehasd va fasde", "ehe");
//        driver.get("https://www.facebook.com/login/");
        Thread.sleep(5000L);

        driver.quit();


    }
}