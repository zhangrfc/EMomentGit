package edu.upenn.cis.mongodb;

import edu.upenn.cis.everyblock.EBLocation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bowen on 1/23/16.
 */
public class DBWrapperTest {
    @Test
    public void createDBTest() {
        DBWrapper.getInstance().updateDB();

        List<EBLocation> locations = DBWrapper.getInstance().getEBLocations();
        for (EBLocation loc : locations) {
            System.out.println("Getting post: " + loc.getSlugName());
            DBWrapper.getInstance().getEBPosts(loc.getSlugName());
        }
    }

}