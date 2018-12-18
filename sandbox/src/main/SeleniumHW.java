
package SeleniumWebdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.Select;

public class SeleniumHW {
	
	

    WebDriver driver ;
	
	//String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
		
		//driver = new ChromeDriver();
		
		driver = new ChromeDriver(new ChromeDriverService.Builder().usingDriverExecutable(new File("/Users/ievgeniiagaidarenko/JAVA/chromedriver 2")).build());
		
		//baseUrl = "https:google.com";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void test() {
		
		driver.get("https://www.yoox.com/us/women");
		driver.findElement(By.xpath("//*[@id=\"sections-menu\"]/li[10]/span")).click();
		
		
	}

	@Test
	public void test1 () throws InterruptedException {
		
		driver.get("https://www.teksystems.com/fr-ca");
		String title = driver.getTitle();
		System.out.println("This is title " + title);
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println("This is URL " + currentUrl);
		
		String UrlTo = "https://www.teksystems.com/fr-ca/services";
		driver.navigate().to(UrlTo);
		
		Thread.sleep(3000);
		driver.navigate().back();
		currentUrl = driver.getCurrentUrl();
		System.out.println("Back Url "+ currentUrl);
		
		Thread.sleep(3000);
		
		driver.navigate().forward();
		currentUrl = driver.getCurrentUrl();
		System.out.println("Forward Url "+ currentUrl);
		
		Thread.sleep(3000);
		driver.navigate().back();
		currentUrl = driver.getCurrentUrl();
		System.out.println("Back Url "+ currentUrl);
		
		driver.navigate().refresh();
		
	}
	
	@Test
	public void test3() {
		
		driver.get("https://www.google.com");
		
		WebElement e1 = driver.findElement(By.id("q"));
		System.out.println("Is this enable e1 " + e1.isEnabled());
		
		WebElement e2 = driver.findElement(By.id("fkbx"));
		System.out.println("Is this enable e2 " + e2.isEnabled());
		
		WebElement e3 = driver.findElement(By.id("fkbx-text"));
		System.out.println("Is this enable e3 " + e3.isEnabled());
		
		e3.sendKeys("yoox");
		
		
	}
	//list of elements
	@Test
	public void test4() throws Exception {
		
		boolean isChecked = false;
		driver.get("https://learn.letskodeit.com/p/practice");
		//x-path ('//input[contains(@type,'radio') and contains(@name, 'cars')]")
		//html/body/div[1]/div/div/div/div/div/div[1]/div[1]/fieldset
		List<WebElement> radioButtons = driver.findElements(By.xpath("//input[contains(@type,'radio') and contains(@name, 'cars')]"));
		
        int size = radioButtons.size();
        System.out.println("Size of the list "+ size);
//		int i ;
		for(int i=0; i<size; i++) {
			
		isChecked = radioButtons.get(i).isSelected();
			
			
			if (!isChecked) {
				radioButtons.get(i).click();
				
				Thread.sleep(3000);
			
			}
	}	
	
	}
	//multiple select elements
	@Test
	public void test5() throws Exception {
		
		driver.get("https://learn.letskodeit.com/p/practice");
		WebElement element = driver.findElement(By.id("carselect"));
		Select sel = new Select(element);
		
		Thread.sleep(3000);
		
		System.out.println("Select by value");
		sel.selectByValue("benz");
		
		Thread.sleep(3000);
		
		System.out.println("Select be Index");
		sel.selectByIndex(2);
		
		Thread.sleep(3000);
		
		System.out.println("Select visible text");
		sel.selectByVisibleText("BMW");
		
		Thread.sleep(3000);
		
		System.out.println("Print full list");
		
		List<WebElement> options = sel.getOptions();
		int size = options.size();
		
		for (int i=0; i<size; i++) {
			String OptionName = options.get(i).getText();
			System.out.println(OptionName);
		}
		
	}
	@Test
	 public void test6() throws Exception {
		driver.get("https://learn.letskodeit.com/p/practice");
				
				WebElement fruit = driver.findElement(By.id("multiple-select-example"));
				Select sel = new Select (fruit);
				
				sel.selectByValue("orange");
				Thread.sleep(3000);
				sel.deselectByValue("orange");
				
				sel.selectByIndex(2);
				Thread.sleep(3000);
				sel.selectByVisibleText("Apple");
				System.out.println("List ");
				
				List<WebElement> selectedOptions = sel.getAllSelectedOptions();
				
				for (WebElement option : selectedOptions) {
					System.out.println(option.getText());
				}
				
				sel.deselectAll();
	}
	//hidden elements
	@Test
	 public void testHiddenElement() throws Exception {
		driver.get("https://learn.letskodeit.com/p/practice");
		
		
		WebElement textBox = driver.findElement(By.id("displayed-text"));
		System.out.println("Element " + textBox.isDisplayed());

		WebElement hideBox = driver.findElement(By.id("hide-textbox"));
		hideBox.click();
		System.out.println("Displayed " + textBox.isDisplayed());
		
		//showbox
		
		Thread.sleep(3000);
		
		WebElement showBox = driver.findElement(By.id("show-textbox"));
		showBox.click();
		
		System.out.println("Element " + textBox.isDisplayed());
		
		
	} 
	@Test
	 public void testHiddenElement2() throws Exception {
		driver.get("https://www.expedia.com/");
		
		WebElement hidElement = driver.findElement(By.id("flight-2-origin-hp-flight"));
		System.out.println("Element " + hidElement.isDisplayed());
	}
	
	@Test
	public void testImplicitException() {
		
		driver.get("https://learn.letskodeit.com/p/practice");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
	}
}
	
