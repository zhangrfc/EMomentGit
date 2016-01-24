package edu.upenn.cis.everyblock;

import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    /**
     * Path to title photo. Default to some fake path.
     */
    private String photoTitleSrc = "emoment.png";

    private List<String> photoSrcs;

    private List<EBComment> comments;

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
            // retrieve comments
            retrieveComments();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(jsPost.toString());
        }
    }

    public EBPost(org.bson.Document postDoc) {
        id = postDoc.getInteger("id");
        title = postDoc.getString("title");
        List<Double> coordinateList = (List<Double>) postDoc.get("coordinate");
        coordinate = new Point(coordinateList.get(0), coordinateList.get(1));
        schema = postDoc.getString("schema");
        postUrl = postDoc.getString("url");
        reactionScore = postDoc.getInteger("reactionScore");
        reactionCount = postDoc.getInteger("reactionCount");
        commentCount = postDoc.getInteger("commentCount");
        if (postDoc.get("photoSrcs") != null) {
            photoSrcs = (List<String>) postDoc.get("photoSrcs");
        }
        if (commentCount > 0) {
            List<Integer> comments = (List<Integer>)postDoc.get("comments");
            for (Integer comment : comments) {
                
            }

        }
    }

    protected void retrieveComments() {
        try {
            if (commentCount == 0) return;
            comments = new ArrayList<>();
            Document doc = Jsoup.connect(getPostUrl()).get();
            Elements commentElems = doc.select("li.comment.cf");
            for (Element elem : commentElems) {
                EBComment comment = new EBComment(elem);
                comments.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<EBComment> getComments() {
        return comments;
    }

    public String getJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("'id':'")
                .append(id)
                .append("', 'title':'")
                .append(title)
                .append("', 'coordinates':[")
                .append(coordinate.x)
                .append(",")
                .append(coordinate.y)
                .append("], 'contnet':'")
                .append(content)
                .append("', 'schema':'")
                .append(schema)
                .append("', 'url':'")
                .append(postUrl)
                .append("', 'reactionScore':'")
                .append(reactionScore)
                .append("', 'reactionCount':'")
                .append(reactionCount)
                .append("', 'commentCount':'")
                .append(commentCount)
                .append("', 'photoTitleSrc':'")
                .append(photoTitleSrc)
                .append("'");
        if (photoSrcs.size() > 0) {
            sb.append(", 'photoSrc':[");
            boolean first = true;
            for (String photoSrc : photoSrcs) {
                if (!first) sb.append(", ");
                else first = false;
                sb.append("'");
                sb.append(photoSrc);
                sb.append("'");
            }
            sb.append("]");
        }

        if (comments.size() > 0) {
            sb.append(", 'comments':[");
            boolean first = true;
            for (EBComment comment : comments) {
                if (!first) sb.append(", ");
                else first = false;
                sb.append("'");
                sb.append(comment.getId());
                sb.append("'");
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }
}
