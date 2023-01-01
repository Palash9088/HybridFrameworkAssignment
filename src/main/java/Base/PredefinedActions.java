package Base;//import java.util.*;

import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PredefinedActions {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    static Logger log = Logger.getLogger(PredefinedActions.class);

    public static void initializeBrowser(String url, String browser) //Selenium 4.6.0 after that
    {
        switch (browser.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver();
                ChromeOptions options = new ChromeOptions();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
            default:
                System.out.println("Illegal Browser Name");
                break;
        }
        driver.manage().window().maximize();
        log.trace("User able to open the browser");
        driver.get(url);
        log.trace("User able to open the " + url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void selectElement(WebElement element, String selectValue) {
        Select select = new Select(element);
        select.selectByValue(selectValue);
    }

    public static String getWindowHandle() {
         /*   if(handle != null)
             log.trace("Driver Launched");
         else
             log.trace("Driver not active");*/
        return driver.getWindowHandle();
    }

    protected List<WebElement> getWebElementList(String locator, boolean isWaitRequired) {
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        return driver.findElements(getByReference(locatorType, locatorValue));
    }

    protected List<String> getWebElementListInString(String locator, boolean isWaitRequired) {
        List<WebElement> webElements = getWebElementList(locator, isWaitRequired);
        List<String> elementListString = new ArrayList<>();
        for (WebElement element : webElements) {
            elementListString.add(element.getText());
        }
        return elementListString;
    }

    protected List<Double> getWebElementListInInteger(String locator, boolean isWaitRequired) {
        List<WebElement> webElements = getWebElementList(locator, isWaitRequired);
        List<Double> elementListInteger = new ArrayList<>();
        for (WebElement element : webElements) {
            elementListInteger.add(Double.parseDouble(element.getText().replace(".", "").replace("%", "")));
        }
        return elementListInteger;
    }

    protected List<Double> getWebElementListInDouble(String locator, boolean isWaitRequired) {
        List<WebElement> webElements = getWebElementList(locator, isWaitRequired);
        List<Double> elementListInteger = new ArrayList<>();
        for (WebElement element : webElements) {
            elementListInteger.add(Double.parseDouble(element.getText().replace(".", "").replace("%", "")) / 100);
        }
        return elementListInteger;
    }

    protected WebElement getElement(String locator, boolean isWaitRequired) {
        WebElement element = null;
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        if (isWaitRequired)
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(getByReference(locatorType, locatorValue)));
        else
            driver.findElement(getByReference(locatorType, locatorValue));
        log.trace("User is trying to get the element");
        return element;
    }


    private String getLocatorType(String locator) {
        String locatorType = null;
        try {
            locatorType = locator.split("]:-")[0].substring(1);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.warn("Check locator splitter in property file");
        }
        return locatorType;
    }

    private String getLocatorValue(String locator) {
        String locatorValue = null;
        try {
            locatorValue = locator.split("]:-")[1];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.warn("Check locator splitter in property file");
        }
        return locatorValue;
    }

    private By getByReference(String locatorType, String locatorValue) {
        switch (locatorType.toUpperCase()) {
            case "XPATH":
                return By.xpath(locatorValue);
            case "ID":
                return By.id(locatorValue);
            case "CLASSNAME":
                return By.className(locatorValue);
            case "PARTIALLINK":
                return By.partialLinkText(locatorValue);
            case "LINKTEXT":
                return By.linkText(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "TAGNAME":
                return By.tagName(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            default:
                log.trace("Invalid Locator Type");
        }
        return null;
    }

    protected String getElementText(WebElement element) {
        String text = element.getText();
        log.trace("User is trying to get Element text");
        return text;
    }

    protected String getAttribute(WebElement element, String attribute) {
        log.trace("User is trying to get attribute value");
        return element.getAttribute(attribute);
    }

    protected String getWebpageTitle() {
        log.trace("User is trying to get page title");
        return driver.getTitle();
    }

    protected void clickOnElement(WebElement element) {
        element.click();
        log.trace("User able to click on Element");
    }

    protected void clickOnElement(String locatorType, boolean isWaitRequired) {
        WebElement element = getElement(locatorType, isWaitRequired);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        log.trace("User able to click on Elements");
    }

    protected void enterText(WebElement element, String textToBe) {
        if (element.isEnabled())
            element.sendKeys(textToBe);
        else element.sendKeys(textToBe);
        log.trace("User able to enter the text");
    }

    protected void clickEnter(String locator, boolean isWaitRequired) {
        WebElement element = getElement(locator, isWaitRequired);
        element.sendKeys(Keys.ENTER);
    }
    protected void navigateBack()
    {
        driver.navigate().back();
    }
    protected void clearElementField(String locator,boolean isWaitRequired)
    {
        WebElement element = getElement(locator,isWaitRequired);
        element.clear();
    }

    public static void takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver; //giving driver capabilities of screenshot
        File screenshotFile = ts.getScreenshotAs(OutputType.FILE); // Choosing file type

        File file = new File("src/test/ScreenShots" + fileName + ".png"); // Making new file
        try {
            Files.copy(screenshotFile, file); // copying file to a folder with File object
        } catch (IOException e) {
            log.error("User not able to copy Screenshot file");
            throw new RuntimeException();
        }
    }

    public static void closeBrowser() {
        driver.close();
        log.trace("User Closed the browser");
    }
}
