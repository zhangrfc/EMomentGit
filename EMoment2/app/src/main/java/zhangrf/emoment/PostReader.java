package zhangrf.emoment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import org.json.*;

/**
 *
 */
public class PostReader {
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

            String resultStr = EBConn.getJSON(apiUrl);

            System.out.println(resultStr);

            arr = new JSONObject(resultStr).getJSONObject("data").getJSONArray("features");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
