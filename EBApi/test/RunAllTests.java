import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**

 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EBLocationTest.class,
        EBPostReaderTest.class,
        EBUtilsTest.class,
        EBPostTest.class
})
public class RunAllTests {
    public static void main(String args[]) {
        JUnitCore.runClasses(RunAllTests.class);
    }
}
