package edu.upenn.cis.mongodb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bowen on 1/23/16.
 */
public class DBWrapperTest {
    @Test
    public void createDBTest() {
        DBWrapper.getInstance().updateDB();
    }
}