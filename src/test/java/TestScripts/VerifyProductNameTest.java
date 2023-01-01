package TestScripts;

import Pages.ProductPageConsumer;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyProductNameTest extends TestBase {

    Logger log = Logger.getLogger(VerifyProductNameTest.class);

    @Test
    public void verifyProductName() {
        //1. Navigate to "https://www.onlyssd.com/".
        ProductPageConsumer productPageConsumer = getProductConsumerPageObj();
        log.info("Actual Title " + productPageConsumer.getTitle());
        String expectedTitle = "Buy SSD in India | Online Laptop SSD Drives | SSD Drive Price";
        Assert.assertEquals(productPageConsumer.getTitle(), expectedTitle);

        //2. Click on Consumer SSD.
        productPageConsumer.clickOnConsumerSSD();

        //3. Apply Filter "In Stock".
        productPageConsumer.filterByInStock();

        //4. Sort by Low to High.
        productPageConsumer.sortByLowToHigh();

        //5. Get all the names of the products
        List<String> productList1 = productPageConsumer.getLowToHighProduct();
        log.info(productList1);
        Assert.assertEquals(productList1.get(0),"Silicon Power US70 1TB PCIe Gen 4×4 NVMe SSD SP01KGBP44US7005");

        //6. sort by High to Low
        productPageConsumer.sortByHighToLow();

        //7. Get all the name of the product
        List<String> productList2 = productPageConsumer.getHighToLowProduct();
        log.info(productList2);
        Assert.assertEquals(productList2.get(0),"SanDisk Professional 144TB G-RAID Shuttle 8 8-Bay RAID Array (8 x 18TB, Thunderbolt 3 & USB 3.2 Gen 2) SDPH48H-144T-NBAAB");
    }
}
