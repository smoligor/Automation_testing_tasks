package Task1;

import Task1.pageObject.TestUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        MainPageTest.class,
        MainPageGuestsTest.class
})
public class TestSuite {
    static TestUtils testUtils;

    @BeforeClass
    public static void init() {
        testUtils = TestUtils.getInstance();
    }

    @AfterClass
    public static void cleanUp() {
        testUtils.createReport();
    }

}
