import org.json.JSONArray;
import org.json.JSONObject;

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

    public EBPost(JSONObject jsPost) {
        try {
            id = jsPost.getInt("id");
            title = jsPost.getString("title");
            JSONObject jsCoordinate = jsPost.getJSONArray("location_coordinates").getJSONObject(0);
            coordinate = new Point(jsCoordinate.getDouble("longitude"), jsCoordinate.getDouble("latitude"));
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(jsPost.toString());
        }
    }

    protected void retrieveImages() {

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
}
