package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;

public class DashboardPage extends PredefinedActions {
    private static DashboardPage dashboardPage;
    PropertyReading dashboardPageProp;

    private DashboardPage() {
        //Private Constructor for Singleton Design Pattern
        dashboardPageProp = new PropertyReading(ConstantPaths.PROP_PATH + "DashboardPageProp.properties");
    }

    public static DashboardPage getDashboardPage() {
        if (dashboardPage == null)
            dashboardPage = new DashboardPage();
        return dashboardPage;
    }

    public String getTitle() {
        return getWebpageTitle();
    }

    public void searchProduct(String brand) {
        clickOnElement(getElement(dashboardPageProp.getValue("searchBox"), true));
        enterText(getElement(dashboardPageProp.getValue("searchBox"), true), brand);
        /*if (result.contains("VIEW ALL RESULTS")) {
            clickOnElement(getElement(dashboardPageProp.getValue("firstProduct"), true));
        } else {
            System.out.println("No result");
        }*/
    }

    private boolean existElement() {
        try {
            getElement(dashboardPageProp.getValue("firstProduct"), true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void clickOnFirstProduct() {
        if (existElement() == true)
            clickOnElement(getElement(dashboardPageProp.getValue("firstProduct"), true));
        else
            System.out.println("Element not present");
    }

    public String getItemName() {
        return getElementText(getElement(dashboardPageProp.getValue("itemName"), true));
    }
    public int getItemPrice()
    {
       return Integer.parseInt(getElementText(getElement(dashboardPageProp.getValue("itemPrice"),true)).replace("₹","").replace(",",""));
    }
    public int getItemDiscountedPrice()
    {
        return Integer.parseInt(getElementText(getElement(dashboardPageProp.getValue("discountPrice"),true)).replace("₹","").replace(",",""));
    }
    public String getDiscountPercentage()
    {
        return (getElementText(getElement(dashboardPageProp.getValue("discountPercentage"),true)));
    }


}
