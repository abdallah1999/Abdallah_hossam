package Screens;

import HelperClass.PropertiesConfiguration;
import WebActions.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class HomeScreen extends BaseActions {

    @FindBy(xpath = "//button[@aria-label = 'Close']")
    private WebElement X_Button;


    @FindBy(xpath = "//div[@data-testid = 'little-search-icon']")
    private WebElement searchIcon;


    @FindBy(id = "bigsearch-query-location-input")
    private WebElement searchText;


    @FindBy(xpath = "//div[@data-testid= 'structured-search-input-field-split-dates-0']")
    private WebElement checkInDate;


    @FindBy(xpath = "//div[@data-testid= 'structured-search-input-field-split-dates-1']")
    private WebElement checkOutData;

    @FindBy(xpath = "//button[@data-testid = 'structured-search-input-search-button']")
    private WebElement applySearchBtn;


    @FindBy(xpath = "//div[@data-testid= 'structured-search-input-field-guests-button']")
    private WebElement guestsButton;

     @FindBy(xpath = "(//span[@class = 'i98ho2o dir dir-ltr'])[2]")
     private WebElement increaseAdult;

    @FindBy(xpath = "(//span[@class = 'i98ho2o dir dir-ltr'])[4]")
    private WebElement increaseChild;

    @FindBy(xpath = "//span[@class=\"ij8oydg dir dir-ltr\"]")
    private List<WebElement> fieldsInSearch;


    public HomeScreen(WebDriver driver) {
        super(driver);
    }


    public void closeThePopUp(){
        try {
            clickOn(X_Button);
        }
        catch (Exception e){
            System.out.println("The PopUp not displayed at this time of run");
        }


    }

    public void clickOnSearchIcon(){

        clickOn(searchIcon);
    }

    public void addSearchText(String txt){
        sendKey(searchText , txt);
    }

    public void PickCheckInDate(){
        clickOn(checkInDate);
        //add one week from date now
        String dateAfterCheckIn =  setDate(1);
        System.out.println(dateAfterCheckIn);
        WebElement checkIN = driver.findElement(By.xpath("//div[@data-testid = 'calendar-day-"+dateAfterCheckIn+"']"));
        clickOn(checkIN);

    }
    public void PickCheckOutDate(){

        //add two weeks from date now
       String dateAfterCheckOut =  setDate(2);
       System.out.println(dateAfterCheckOut);
       WebElement checkOut = driver.findElement(By.xpath("//div[@data-testid = 'calendar-day-"+dateAfterCheckOut+"']"));
       clickOn(checkOut);

    }
   public void clickOnAddGuests(){
        clickOn(guestsButton);
   }
    public void AddAdults(int n){
        for (int i = 0 ; i < n ; i++){
            clickOn(increaseAdult);
        }
    }


    public void applySearch(){
        clickOn(applySearchBtn);

    }
    public void AddChild(int n){
        for (int i = 0 ; i < n ; i++){
            clickOn(increaseChild);
        }
    }

    public boolean validateAllKeywordSearchAreCorrect() throws IOException, InterruptedException {
        Thread.sleep(6000);
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();

            System.out.println(fieldsInSearch.get(0).getText()+ " >>>>>" + 0);

            String [] arr = propertiesConfiguration.getProperty("location").split(",");
            if(!(fieldsInSearch.get(0).getText()).contains(arr[0])){
                return false;
            }

            int firstChoise =  Integer.valueOf(propertiesConfiguration.getProperty("adults"));
            int secondChoise = Integer.valueOf(propertiesConfiguration.getProperty("child"));

            int total =  firstChoise + secondChoise;
            if(!(fieldsInSearch.get(0).getText()).contains(String.valueOf(total))){
                return false;
            }
               return true;
    }
    private String setDate(int numberOfWeeks){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, numberOfWeeks);
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        return DateFor.format(calendar.getTime());

    }



}
