package Tests;

import HelperClass.PropertiesConfiguration;
import Screens.HomeScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.text.ParseException;
public class BaseTest {

    private WebDriver driver;
    protected PropertiesConfiguration propertiesConfiguration;

    @BeforeSuite
    public void setUp() throws IOException {
        propertiesConfiguration = new PropertiesConfiguration();
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(propertiesConfiguration.getProperty("url"));

        Assert.assertEquals(driver.getCurrentUrl() , propertiesConfiguration.getProperty("url") );
    }
    @Test
    public void SearchByDate() throws ParseException, IOException, InterruptedException {
       HomeScreen homeScreen = new HomeScreen(driver);
       homeScreen.closeThePopUp();
       homeScreen.clickOnSearchIcon();
       homeScreen.addSearchText(propertiesConfiguration.getProperty("location"));
       homeScreen.PickCheckInDate();
       homeScreen.PickCheckOutDate();
       homeScreen.clickOnAddGuests();
       homeScreen.AddAdults(Integer.valueOf(propertiesConfiguration.getProperty("adults")));
       homeScreen.AddChild(Integer.valueOf(propertiesConfiguration.getProperty("child")));
       homeScreen.applySearch();
       Assert.assertTrue(homeScreen.validateAllKeywordSearchAreCorrect());
    }
    @AfterSuite
    public void close(){
        driver.quit();
    }


}
