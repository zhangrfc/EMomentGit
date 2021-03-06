package edu.upenn.cis.everyblock;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**

 */
public class EBLocationTest {
    @Test
    public void containTest() {
        try {
            String apiUrl = "https://api.everyblock.com/gis/philly/neighborhoods/.json?token="
                    + EBUtils.getInstance().getToken();
            String resultStr = EBConn.getJSON(apiUrl);
            JSONArray arr = new JSONObject(resultStr).getJSONObject("data").getJSONArray("features");

            for (int i=0; i<arr.length(); ++i) {
                // test polygon
                String polygonName = arr.getJSONObject(i).getJSONObject("properties").getString("name");

                if (polygonName.compareTo("University City") == 0) {
                    JSONArray coordinates = arr.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates")
                            .getJSONArray(0);

                    Boundary boundary = new Boundary(coordinates);
                    Point testPt = new Point(-75.1985766682, 39.9502619446);

                    Assert.assertEquals(true, boundary.contains(testPt));
                    return;
                }
            }
            assertEquals("Found University City", "Not found");
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("success", "exception");
        }
    }

    @Test
    public void slugTest() {
        EBLocation location = new EBLocation("Allegheny West");
        Assert.assertEquals("allegheny-west", location.getSlugName());
    }
}