package TestScripts;//import java.util.*;

import Pages.DashboardPage;
import org.testng.annotations.Test;

public class VerifyProductPageSearchTest extends TestBase {

    @Test
    public void verifySearchResult() {
        DashboardPage dashboardPage = getProductDashboardObj();
        dashboardPage.getTitle();
        dashboardPage.searchProduct("Adata");
        dashboardPage.clickOnFirstProduct();
        String name = dashboardPage.getItemName();
        int price = dashboardPage.getItemPrice();
        int discountedPrice = dashboardPage.getItemDiscountedPrice();
        String discountPercentage = dashboardPage.getDiscountPercentage();

        System.out.println("Item name is :" + name + "\n"
                + "Price is : " + price + "\n" +
                "Discounted Price is : " + discountedPrice + "\n" +
                "Discount percentage is : " + discountPercentage);
    }
}
