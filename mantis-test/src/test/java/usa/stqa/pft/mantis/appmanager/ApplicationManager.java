package usa.stqa.pft.mantis.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApplicationManager {
	private WebDriver wd;
	private String browser;
	private final Properties properties;
	private RegistrationHelper registrationHelper;
	private FtpHelper ftp;
	private SoapHelper soapHelper;
	private Logger logger;

	public ApplicationManager(String browser)  {

		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
	}

	public void stop() {
		if (wd != null){
			wd.quit();
		}
	}

	@SuppressWarnings("WeakerAccess")
	public Logger log() {
		if (logger == null) {
			logger = LoggerFactory.getLogger(ApplicationManager.class);
		}
		return logger;
	}

	public HttpSession newSession(){
		return new HttpSession(this);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public RegistrationHelper registration() {
		if (registrationHelper == null){
			registrationHelper =  new RegistrationHelper(this);
		}
		return registrationHelper;
	}

	public FtpHelper ftp(){
		if (ftp == null){
			ftp = new FtpHelper(this);
		}
		return ftp;
	}

	public WebDriver getDriver() {
		if (wd == null){
			System.setProperty("webdriver.chrome.driver",
					"/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
			System.setProperty("webdriver.gecko.driver",
					"/Users/ievgeniiagaidarenko/JAVA/geckodriver ");

			if (browser.equals(BrowserType.CHROME)) {
				wd = new ChromeDriver();
			} else if (browser.equals(BrowserType.FIREFOX)) {
				wd = new FirefoxDriver();
			} else if (browser.equals(BrowserType.SAFARI)) {
				wd = new SafariDriver();
			}
			wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			wd.get(properties.getProperty("web.baseURL"));
		}
		return wd;
	}

	public SoapHelper soap(){
		if (soapHelper == null){
			soapHelper = new SoapHelper(this);
		}
		return soapHelper;
	}
}