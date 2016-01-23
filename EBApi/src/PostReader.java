import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.awt.Polygon;
import java.nio.Buffer;

import org.json.*;

/**
 *
 */
public class PostReader {

    public static String getJSON(URL url) {
        try {
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        try {
            URL apiUrl = new URL("https://api.everyblock.com/content/.json?token=70f582975282b7846aa9cf5a55ac09c1d116e5c6");
            URLConnection conn = apiUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder strBuilder = new StringBuilder();

            while ((line = in.readLine()) != null) {
                strBuilder.append(line);
            }

            System.out.println(strBuilder.toString());

            JSONArray arr = new JSONArray(strBuilder.toString());
            for (int i = 0; i < arr.length(); ++i) {
                System.out.println("Short name: " + arr.getJSONObject(i).getString("short_name")
                        + " Metro name: " + arr.getJSONObject(i).getString("metro_name")
                );
            }

            // polygon
            apiUrl = new URL("https://api.everyblock.com/gis/philly/neighborhoods/.json?token=70f582975282b7846aa9cf5a55ac09c1d116e5c6");
            conn = apiUrl.openConnection();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            strBuilder = new StringBuilder();

            String resultStr = getJSON(apiUrl);

            System.out.println(resultStr);

            arr = new JSONObject(resultStr).getJSONObject("data").getJSONArray("features");
            for (int i=0; i<arr.length(); ++i) {
                //System.out.println(arr.getJSONObject(i).toString());

                // test polygon
                String polygonName = arr.getJSONObject(i).getJSONObject("properties").getString("name");
                //System.out.println(polygonName);

                if (polygonName.compareTo("University City") == 0) {
                    System.out.println("in");
                    JSONArray coordinates = arr.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates")
                            .getJSONArray(0);
                    System.out.println(coordinates.toString());

                    Boundary boundary = new Boundary(coordinates);
                    Point testPt = new Point(-75.1985766682, 39.9502619446);

                    if (boundary.contains(testPt)) {
                        System.out.println("success");
                    } else System.out.println("faile");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
