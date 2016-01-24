package zhangrf.emoment;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**

 */
public class EBUtils {
    private static EBUtils utils = null;

    private String token;
    private String urlPrefix = "https://api.everyblock.com/content/philly/locations/";
    private String urlPostfix = "/timeline/.json?token=";

    private String neighborsUrl = "https://api.everyblock.com/content/philly/neighborhoods/.json?token=";

    private EBUtils() {
        init();
    }

    private void init() {
        try {
            File confFile = new File("env.conf");
            BufferedReader in = new BufferedReader(new FileReader(confFile));

            token = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EBUtils getInstance() {
        if (utils == null) {
            utils = new EBUtils();
        }
        return utils;
    }

    public String getSlugTimelineUrl(String slugName) {
        return urlPrefix + slugName + urlPostfix + token;
    }

    public String getNeighborsUrl() {
        return neighborsUrl + token;
    }
}
