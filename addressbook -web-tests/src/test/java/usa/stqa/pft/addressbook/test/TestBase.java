package usa.stqa.pft.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import usa.stqa.pft.addressbook.appmanager.ApplicationManager;
import usa.stqa.pft.addressbook.model.GroupData;
import usa.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

//Use Chrome or Firefox driver
public class TestBase {
//    protected static final ApplicationManager app
//            = new ApplicationManager(System.getProperty("browser"));
Logger logger = LoggerFactory.getLogger(TestBase.class);
protected static final ApplicationManager app
        = new ApplicationManager(BrowserType.CHROME);
    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();

    }
    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object [] p){
        logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));

    }
    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g) ->
                    new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }
}