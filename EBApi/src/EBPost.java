import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**

 */
public class EBPost {
    private int id;
    private String title;
    private Point coordinate;
    private String content;
    private String schema;
    private String postUrl;
    private int reactionScore;
    private int reactionCount;
    private int commentCount;
    /**
     * Distance between user and this post.
     */
    private double distance;

    private List<String> photoSrcs;

    public EBPost(JSONObject jsPost) {
        try {
            id = jsPost.getInt("id");
            title = jsPost.getString("title");
            JSONObject jsCoordinate = jsPost.getJSONArray("location_coordinates")
                    .getJSONObject(0);
            coordinate = new Point(
                    jsCoordinate.getDouble("longitude"),
                    jsCoordinate.getDouble("latitude"));
            if (jsPost.getJSONObject("attributes").has("comment")) {
                content = jsPost.getJSONObject("attributes").getString("comment");
            } else if (jsPost.getJSONObject("attributes").has("excerpt")) {
                content = jsPost.getJSONObject("attributes").getString("excerpt");
            }
            schema = jsPost.getString("schema");
            postUrl = jsPost.getString("url");
            reactionScore = jsPost.getInt("reaction_score");
            reactionCount = jsPost.getInt("reaction_count");
            commentCount = jsPost.getInt("comment_count");
            // retrieve photos
            retrievePhotos();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(jsPost.toString());
        }
    }

    /**
     * First go to url + "/photos".
     * Parse the webpage and retrieve link to photos.
     * Download the photos (or store the link).
     */
    protected void retrievePhotos() {
        try {
            photoSrcs = new ArrayList<>();
            Document doc = Jsoup.connect(getPostUrl() + "photos")
                    .get();
            Elements imgSrcs = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element image : imgSrcs) {
                photoSrcs.add(image.attr("src"));
            }

        } catch (HttpStatusException e) {
            if (e.getStatusCode() != 404) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public String getContent() {
        return content;
    }

    public String getSchema() {
        return schema;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public int getReactionScore() {
        return reactionScore;
    }

    public int getReactionCount() {
        return reactionCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public List<String> getPhotoSrcs() {
        return photoSrcs;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
