package edu.upenn.cis;

import edu.upenn.cis.everyblock.EBLocationTest;
import edu.upenn.cis.everyblock.EBPostReaderTest;
import edu.upenn.cis.everyblock.EBPostTest;
import edu.upenn.cis.everyblock.EBUtilsTest;
import edu.upenn.cis.mongodb.DBWrapperTest;
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
        EBPostTest.class,
        DBWrapperTest.class
})
public class RunAllTests {
    public static void main(String args[]) {
        JUnitCore.runClasses(RunAllTests.class);
    }
}
