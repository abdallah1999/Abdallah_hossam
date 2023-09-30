package WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseActions {

    protected WebDriver driver;

    public BaseActions(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver , this);
    }
    protected void waitForElement(WebElement element){
            WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(element));

    }



    protected void waitForElements(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    protected void clearField(WebElement element){
        waitForElement(element);
        element.clear();
    }


    protected void clickOn(WebElement element){
        waitForElement(element);
        element.click();
    }


    protected void sendKey(WebElement element , String text){
        clearField(element);
        element.sendKeys(text);
    }

}
