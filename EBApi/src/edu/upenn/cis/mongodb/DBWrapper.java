package edu.upenn.cis.mongodb;


import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import edu.upenn.cis.everyblock.*;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bowen on 1/23/16.
 */
public class DBWrapper {
    private static String DB_NAME = "emoment";
    private static DBWrapper dbWrapper;
    private MongoClient mongoClient;
    private MongoDatabase db;

    private HashMap<String, EBLocation> nameLocMap;

    private DBWrapper() {
        init();
    }

    private void init() {
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase(DB_NAME);
    }

    public static DBWrapper getInstance() {
        if (dbWrapper == null) {
            dbWrapper = new DBWrapper();
        }
        return dbWrapper;
    }

    public List<EBPost> getEBPosts(String slugName) {
        List<EBPost> posts = new ArrayList<>();

        FindIterable<Document> iterable = db.getCollection("posts").find();
        MongoCursor<Document> iter = iterable.iterator();
        while (iter.hasNext()) {
            Document postDoc = iter.next();
            EBPost post = new EBPost(postDoc);
            posts.add(post);
        }

        return posts;
    }

    public List<EBLocation> getEBLocations() {
        List<EBLocation> locations = new ArrayList<>();

        FindIterable<Document> iterable = db.getCollection("locations").find();
        MongoCursor<Document> iter = iterable.iterator();
        while (iter.hasNext()) {
            Document locDoc = iter.next();
            EBLocation loc = new EBLocation(locDoc);
            locations.add(loc);
        }
        return locations;
    }

    public void updateDB() {
        // drop db
        db.drop();
        // create db
        db = mongoClient.getDatabase(DB_NAME);
        // create locations
        updateEBLocations();
        // create posts and comments
        updateEBPostsAndComments();
    }

    private void updateEBLocations() {
        db.createCollection("locations");

        // First retrieve all location infos. e.g. names, slug name, id etc.
        nameLocMap = new HashMap<>();

        String locsUrl = EBUtils.getInstance().getNeighborsUrl();
        String locsJSStr = EBConn.getJSON(locsUrl);
        JSONArray locsArr = new JSONArray(locsJSStr);
        for (int i=0; i<locsArr.length(); ++i) {
            JSONObject item = locsArr.getJSONObject(i);
            int id = item.getInt("id");
            String name = item.getString("name");
            String slugName = item.getString("slug");
            EBLocation loc = new EBLocation(name);
            loc.setSlugName(slugName);
            loc.setId(id);
            nameLocMap.put(name, loc);
        }

        // Retrieve boundaries of locations.
        String apiUrl = "https://api.everyblock.com/gis/philly/neighborhoods/.json?token="
                + EBUtils.getInstance().getToken();
        String resultStr = EBConn.getJSON(apiUrl);
        JSONArray arr = new JSONObject(resultStr).getJSONObject("data").getJSONArray("features");

        for (int i=0; i<arr.length(); ++i) {
            String polygonName = arr.getJSONObject(i).getJSONObject("properties").getString("name");

            if (nameLocMap.containsKey(polygonName)) {
                JSONArray coordinates = arr.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates")
                        .getJSONArray(0);
                Boundary boundary = new Boundary(coordinates);
                EBLocation loc = nameLocMap.get(polygonName);
                loc.setBoundary(boundary);
                nameLocMap.put(polygonName, loc);
            } else {
                System.out.println("Location " + polygonName + " not found.");
            }
        }

        for (Map.Entry<String, EBLocation> pair : nameLocMap.entrySet()) {
            String name = pair.getKey();
            EBLocation loc = pair.getValue();
            System.out.println("JSON: " + loc.getJSON());
            db.getCollection("locations").insertOne(Document.parse(loc.getJSON()));
        }
    }

    private void updateEBPostsAndComments() {
        db.createCollection("posts");
        db.createCollection("comments");

        List<EBPost> posts = new ArrayList<>();
        List<Document> postsDoc = new ArrayList<>();
        List<Document> commentsDoc = new ArrayList<>();

        // Retrieve all Posts and comments from each location
        for (Map.Entry<String, EBLocation> pair : nameLocMap.entrySet()) {
            String locName = pair.getKey();
            EBLocation loc = pair.getValue();
            EBPostReader reader = new EBPostReader(loc);

            System.out.println("Start retrieving posts for neighbor " + locName);
            reader.retrieveAllEBPosts();
            posts.addAll(reader.getEbCompletePostList());
        }

        for (EBPost post : posts) {
            // Generate JSON for posts
            Document postDoc = Document.parse(post.getJSON());
            postsDoc.add(postDoc);
            // Generate JSON for comments
            if (post.getCommentCount() > 0) {
                for (EBComment comment : post.getComments()) {
                    Document commentDoc = Document.parse(comment.getJSON());
                    commentsDoc.add(commentDoc);
                }
            }
        }

        db.getCollection("posts").insertMany(postsDoc);
        db.getCollection("comments").insertMany(commentsDoc);
    }

    public static void main(String args[]) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("emoment");
    }
}
