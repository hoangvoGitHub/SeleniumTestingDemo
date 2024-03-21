import org.example.SignInPage;
import org.example.common.ExcelHelper;
import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SignInGridTest {

    private static ExcelHelper excel;
//    private static SignInPage signInPage;
    private static WebDriver driver;

    private static final String hubUrl = "http://172.22.252.109:4444/";

    @BeforeSuite
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        driver = new RemoteWebDriver(new URL(hubUrl), desiredCapabilities);
        excel = new ExcelHelper();
    }

//    @Test
//    public void signInSingleTest() throws InterruptedException {
//        SignInPage signInPage = new SignInPage(driver);
//        final SignInPage.LoginResult expectedResult = SignInPage.LoginResult.Success;
//        final SignInPage.LoginResult actualResult = signInPage.login(
//                "", ""
//        );
//        Assert.assertEquals(expectedResult, actualResult);
//
//    }

    @Test
    public void signInPageWithExcel() throws Exception {

        SignInPage signInPage = new SignInPage(driver);

        // Setup excel file path
        excel.setExcelFile("src/test/resources/Book1.xlsx", "Sheet1");

        // Read data from excel file
        for (int i = 1; i < 4; i++) {
            final SignInPage.LoginResult result = SignInPage.LoginResult.valueOf(excel.getCellData("expected result", i));
            final SignInPage.LoginResult logInResult = signInPage.login(
                    excel.getCellData("username", i),
                    excel.getCellData("password", i)
            );
            Assert.assertEquals(result, logInResult);
            Thread.sleep(5000L);
        }


    }

    @AfterSuite
    public static void tearsDown() {
        driver.quit();
    }


}