package usa.stqa.pft.mantis.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import usa.stqa.pft.mantis.appmanager.ApplicationManager;

public class TestBase {


protected static final ApplicationManager app
        = new ApplicationManager(BrowserType.CHROME);
    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.stop();

    }
}