import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Selenium Testing");

        System.setProperty("webdriver.chrome.driver",
                "/home/morel/Learning/Java/Testing/Selenium/src/drivers/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        System.out.println(driver.getTitle());
        Thread.sleep(1000);
        driver.quit();
    }
}
