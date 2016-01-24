package edu.upenn.cis.everyblock;

import org.junit.Test;

import static org.junit.Assert.*;

/**

 */
public class EBUtilsTest {
    @Test
    public void tokenTest() {
        String url = EBUtils.getInstance().getNeighborsUrl();
        assertNotEquals("+", url.charAt(url.length()-1));
    }
}