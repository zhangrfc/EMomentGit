package zhangrf.emoment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**

 */
public class EBPostReader {
    private EBLocation curLocation;
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

    public void retrieveAllEBPosts() throws Exception{
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
                EBPost post = new EBPost(arr.getJSONObject(i));
                ebCompletePostList.add(post);
            }

            pageNum++;
        }

    }

    public List<EBPost> retrieveSortedEBPosts() throws Exception{
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
        //Arrays.sort(ebCompletePostList,EBPostDistComparator.INSTANCE);
        //ebCompletePostList.sort(EBPostDistComparator.INSTANCE);
        Collections.sort(ebCompletePostList,EBPostDistComparator.INSTANCE);
        //List<Integer> ll = new ArrayList<Integer>();
        //Collections.sort(ll,EBPostDistComparator.INSTANCE);
        //ll.s
        return ebCompletePostList;
    }

    protected boolean hasNextPage(JSONObject jsPage) {
        return !jsPage.isNull("next");
    }

    protected List<EBPost> getEbCompletePostList() {
        return ebCompletePostList;
    }

    protected int getPostCount() {
        return postCount;
    }
}
