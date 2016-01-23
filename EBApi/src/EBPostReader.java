import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class EBPostReader {
    private EBLocation curLocation;
    private List<EBPost> ebCompletePostList;
    private int postCount;


    public EBPostReader(EBLocation location) {
        curLocation = location;
    }

    public void setEBLocation(EBLocation location) {
        curLocation = location;
    }

    public void getEBPosts() {
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
