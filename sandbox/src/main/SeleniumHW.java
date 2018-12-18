
package SeleniumWebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumHW1 {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "/Users/ievgeniiagaidarenko/JAVA/chromedriver 2" );
		
WebDriver driver = new ChromeDriver();
driver.get("https://selenium2.ru/articles/136-installing-xampp.html");
driver.manage().window().maximize();

Thread.sleep(4000);

driver.close();
	}
	

}
