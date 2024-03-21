package org.example;

import org.example.common.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardPage extends WebPage {

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getUrl() {
        return "https://www.facebook.com";
    }
}
