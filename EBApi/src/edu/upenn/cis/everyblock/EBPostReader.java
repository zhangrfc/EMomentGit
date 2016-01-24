package edu.upenn.cis.everyblock;

import edu.upenn.cis.mongodb.DBWrapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**

 */
public class EBPostReader {
    private EBLocation curLocation;
    /**
     * longitude and latitude of current user location.
     * Default to be within University City.
     */
    private Point curCoordinate = new Point(-75.1985766682, 39.9502619446);
    private List<EBPost> ebCompletePostList;
    private int postCount;

    private static class EBPostDistComparator implements Comparator<EBPost> {
        static EBPostDistComparator INSTANCE = new EBPostDistComparator();
        @Override
        public int compare(EBPost post1, EBPost post2) {
            return Double.compare(post1.getDistance(), post2.getDistance());
        }
    }

    public EBPostReader(EBLocation location) {
        curLocation = location;
    }

    public void setEBLocation(EBLocation location) {
        curLocation = location;
    }

    public void retrieveAllEBPosts() {
        ebCompletePostList = new ArrayList<>();

        String curSlugTLUrl = EBUtils.getInstance()
                .getSlugTimelineUrl(curLocation.getSlugName());
        boolean readAllPages = false;
        int pageNum = 1;

        while (!readAllPages) {
            System.out.println("Reading page " + pageNum + " ...");
            String jsStr = EBConn.getJSON(curSlugTLUrl + "&page=" + pageNum);
            JSONObject jsPage = new JSONObject(jsStr);
            // check if there is more pages
            if (!hasNextPage(jsPage)) {
                readAllPages = true;
            }

            // retrieve count
            postCount = jsPage.getInt("count");

            // retrieve posts
            JSONArray arr = jsPage.getJSONArray("results");
            for (int i=0; i<arr.length(); ++i) {
                EBPost post = new EBPost(arr.getJSONObject(i), curLocation.getSlugName());
                ebCompletePostList.add(post);
            }

            pageNum++;
        }

    }

    public List<EBPost> retrieveSortedEBPosts() {
        if (ebCompletePostList == null) {
            retrieveAllEBPosts();
        }
        // Compute distance for all posts
        // Get current location coordinate. For now fake one.
        Point curPoint = new Point(-75.1985766682, 39.9502619446);
        for (EBPost post : ebCompletePostList) {
            post.setDistance(curPoint.sqrDistTo(post.getCoordinate()));
        }

        // Sort posts based on distance
        ebCompletePostList.sort(EBPostDistComparator.INSTANCE);
        return ebCompletePostList;
    }

    public void refreshLocation() {
        // Check if still in current location
        if (curLocation.contains(curCoordinate)) {
            return;
        }
        List<EBLocation> locations = DBWrapper.getInstance().getEBLocations();
        for (EBLocation loc : locations) {
            if (loc.contains(curCoordinate)) {
                curLocation = loc;
                return;
            }
        }
    }

    public List<EBPost> getEbCompletePostList() {
        return ebCompletePostList;
    }

    protected boolean hasNextPage(JSONObject jsPage) {
        return !jsPage.isNull("next");
    }

    protected int getPostCount() {
        return postCount;
    }
}
