import org.example.SignInPage;
import org.example.common.ExcelHelper;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class SignInTest {

    private static ExcelHelper excel;
    private static SignInPage signInPage;
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        excel = new ExcelHelper();
        driver = new FirefoxDriver();
    }

    @Test
    public void signInPage() throws Exception {
        excel = new ExcelHelper();
        driver = new FirefoxDriver();


        // Setup excel file path
        excel.setExcelFile("src/test/resources/Book1.xlsx", "Sheet1");

        signInPage = new SignInPage(driver);

        // Read data from excel file
        for (int i = 1; i < 4; i++) {
            final SignInPage.LoginResult expectedResult = SignInPage.LoginResult.valueOf(excel.getCellData("expected result", i));
            final SignInPage.LoginResult actualResult = signInPage.login(
                    excel.getCellData("username", i),
                    excel.getCellData("password", i)
            );
            Assert.assertEquals(expectedResult, actualResult);
            Thread.sleep(5000L);
        }


        Thread.sleep(2000);
    }

    @AfterClass
    public static void tearsDown(){
        driver.quit();
    }


}
