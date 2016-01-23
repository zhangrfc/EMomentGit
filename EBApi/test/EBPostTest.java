import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**

 */
public class EBPostTest {
    @Test
    public void getPostTest() {
        String slugTLUrl = EBUtils.getInstance()
                .getSlugTimelineUrl("university-city");
        String jsStr = EBConn.getJSON(slugTLUrl);
        JSONObject jsPost = new JSONObject(jsStr).getJSONArray("results").getJSONObject(0);

        EBPost post = new EBPost(jsPost);
        assertNotEquals(null, post.getId());
        assertNotEquals(null, post.getCommentCount());
        assertNotEquals(null, post.getContent());
        assertNotEquals(null, post.getCoordinate());
        assertNotEquals(null, post.getPostUrl());
        assertNotEquals(null, post.getReactionCount());
        assertNotEquals(null, post.getReactionScore());
        assertNotEquals(null, post.getSchema());
        assertNotEquals(null, post.getTitle());
    }
}