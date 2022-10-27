import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Selenium Testing");

        // Path needs to be updated if building on local machine
        System.setProperty("webdriver.chrome.driver",
                "/home/morel/Learning/Java/Testing/Selenium/src/drivers/chromedriver");

        WebDriver driver = new ChromeDriver();
        /**
         * 1. Go to “http://automationpractice.com/index.php” and click on Sign-in
         */

        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.linkText("Sign in")).click();

        /**
         * 2. Sign-in using the following credentials
         * a. Username:techsystems@safeboda.com
         * b. Password:Safeboda123$
         */

        driver.findElement(By.id("email")).sendKeys("techsystems@safeboda.com");
        driver.findElement(By.id("passwd")).sendKeys("Safeboda123$");
        driver.findElement(By.id("SubmitLogin")).click();

        driver.findElement(By.className("logo")).click();
        driver.findElement(By.className("homefeatured")).click();

        /**
         * 3. On the landing page under POPULAR category we see a list of apparels. Get
         * the
         * Label
         * and associated Price of those items. Fetch items and sort it by price [Low to
         * High] and
         * print it on the Console [Label and Price]
         * A 2D ArrayList throws an Exception
         */
        String pricesClass = "//span[@itemprop='price']";
        String labelNames = "//a[@class='product-name']";
        List<WebElement> prices = driver.findElements(By.xpath(pricesClass));
        List<WebElement> names = driver.findElements(By.xpath(labelNames));

        try {
            for (int i = 0; i <= prices.size(); i++) {
                System.out.println(prices.get(i).getText());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            for (int i = 0; i <= names.size(); i++) {
                System.out.println(names.get(i).getText());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * 4. Scroll Up - Navigate to Women >> Tops>>
         */
        // Get the "Women" text
        WebElement womenText = driver.findElement(By.xpath("//a[@class='sf-with-ul']"));
        Actions action = new Actions(driver);

        // Performing the mouse hover action on the target element.
        action.moveToElement(womenText).perform();

        // Get the "Top" text
        driver.findElement(By.xpath("(//a[@class='sf-with-ul'])[2]")).click();

        driver.findElement(By.linkText("T-shirts")).click();

        /**
         * 5. Go to Catalog and filter out a dress Size [L]>>Color [Orange]>>Styles
         * [Casual]>> Set
         * Range: $16.00 - $17.00
         */

        driver.findElement(By.xpath("//p[@class='title_block']"));

        // Find Size (L)
        driver.findElement(By.xpath("//ul[@id='ul_layered_id_attribute_group_1']/li[1]/label[1]/a[1]")).click();

        // Find color orange
        driver.findElement(By.xpath("//ul[@id='ul_layered_id_attribute_group_3']/li[1]/label[1]/a[1]")).click();

        // Find style casual
        driver.findElement(By.xpath("//ul[@id='ul_layered_id_feature_6']/li[1]/label[1]/a[1]")).click();

        // Set Range: $16.00 - $17.00
        WebElement slider = driver.findElement(By.cssSelector("div#layered_price_slider"));
        Thread.sleep(2000);
        Actions sliderAction = new Actions(driver);
        sliderAction.dragAndDropBy(slider, 16, 17);

        /**
         * 6. Once the entry is found click More.
         */

        WebElement moreText = driver.findElement(By.xpath("//ul[contains(@class,'product_list grid')]//li"));
        Actions moreAction = new Actions(driver);

        moreAction.moveToElement(moreText).perform();

        // Sleep for 3 seconds. Otherwise, the "More" button will be uninteractive
        Thread.sleep(3000);

        driver.findElement(By
                .cssSelector("div#center_column>ul>li>div>div:nth-of-type(2)>div:nth-of-type(2)>a:nth-of-type(2)>span"))
                .click();

        /**
         * 7. Set Quantity = 3 >> Size = L >>Color=Orange
         */

        WebElement quantityInput = driver.findElement(By.xpath("//input[@class='text']"));
        // Change quantity value
        quantityInput.clear();
        quantityInput.sendKeys("3");

        // Change the select value

        WebElement group = driver.findElement(By.cssSelector("select#group_1"));
        new Select(group).selectByVisibleText("L");

        // Change the color
        driver.findElement(By.xpath("//a[@class='color_pick selected']")).click();

        /**
         * 8. Click Add to Cart
         */

        driver.findElement(By.xpath("(//button[@type='submit']//span)[3]")).click();

        /**
         * 9. Verify quantity, size, color, and total cost of the product on pop-up
         */
        String colorAndSize = driver.findElement(By.cssSelector("span#layer_cart_product_attributes")).getText();
        if (colorAndSize == null) {
            System.out.println("The selected size and color do not exist");
        } else {
            System.out.println("Color and Size:" + colorAndSize);
        }

        String quantityString = driver.findElement(By.cssSelector("span#layer_cart_product_quantity")).getText();
        if (quantityString == null) {
            System.out.println("The selected quantity do not exist");
        } else {
            System.out.println("Quantity is" + quantityString);
        }

        String totalString = driver.findElement(By.xpath("(//span[text()='$49.53'])[3]")).getText();
        if (totalString == null) {
            System.out.println("The total does not exist");
        } else {
            System.out.println("Total is" + totalString);
        }

        /**
         * 10. Find out shipping cost
         */

        String shippingCost = driver.findElement(By.xpath("(//span[text()='$2.00'])[2]")).getText();
        System.out.println(shippingCost);
        String totalCost = driver.findElement(By.xpath("(//span[text()='$51.53'])[2]")).getText();
        System.out.println(totalCost);
    }
}
