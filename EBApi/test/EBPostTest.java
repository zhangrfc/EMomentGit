import org.json.JSONArray;
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
        JSONObject jsPost = new JSONObject(jsStr).getJSONArray("results")
                .getJSONObject(0);

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

    @Test
    public void getPostImageTest() {
        String slugTLUrl = EBUtils.getInstance()
                .getSlugTimelineUrl("university-city");
        String jsStr = EBConn.getJSON(slugTLUrl);

        JSONArray arr = new JSONObject(jsStr).getJSONArray("results");
        for (int i=0; i<arr.length(); ++i) {
            // Find the snow post
            if (arr.getJSONObject(i).getInt("id") == 2219385) {
                JSONObject jsPost = arr.getJSONObject(i);
                EBPost post = new EBPost(jsPost);
                // Check images.
                assertEquals(2, post.getPhotoSrcs().size());
                assertEquals("https://everyblock.s3.amazonaws.com/photos/c/c6a92ce9/1878/4a1ff713c9a69da9a1806d0d791a138c-med.jpg",
                        post.getPhotoSrcs().get(0));
                assertEquals("https://everyblock.s3.amazonaws.com/photos/c/c6a92ce9/1877/a3f48d2e15b1cf4b47a014fbb36ae74c-med.jpg",
                        post.getPhotoSrcs().get(1));
            }
        }
    }

    @Test
    public void getCommentsTest() {
        String slugTLUrl = EBUtils.getInstance()
                .getSlugTimelineUrl("university-city");
        String jsStr = EBConn.getJSON(slugTLUrl);

        JSONArray arr = new JSONObject(jsStr).getJSONArray("results");
        for (int i=0; i<arr.length(); ++i) {
            // Find the snow post
            if (arr.getJSONObject(i).getInt("id") == 2219331) {
                JSONObject jsPost = arr.getJSONObject(i);
                EBPost post = new EBPost(jsPost);
                post.retrieveComments();
                // Check comments
                assertEquals(post.getCommentCount(), post.getComments().size());
                EBComment comment1 = post.getComments().get(0);
                assertEquals(8560, comment1.getId());
                assertEquals("https://d3nlbyo2tmce3j.cloudfront.net/images/avatars/1/medium.jpg", comment1.getAvatarSrc());
                assertEquals("Chaoyi Zha", comment1.getUsername());
                assertEquals("wow, great!", comment1.getContent());

                EBComment comment2 = post.getComments().get(1);
                assertEquals(8562, comment2.getId());
                assertEquals("https://s3.amazonaws.com/everyblock/users/222620/medium.jpg", comment2.getAvatarSrc());
                assertEquals("Bowen Bao", comment2.getUsername());
                assertEquals("awesome!", comment2.getContent());
            }
        }
    }
}