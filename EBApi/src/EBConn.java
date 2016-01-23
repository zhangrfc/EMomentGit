import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**

 */
public class EBConn {
    public static String getJSON(String url) {
        try {
            return getJSON(new URL(url));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getJSON(URL url) {
        try {
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
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
}
