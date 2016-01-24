package edu.upenn.cis.everyblock;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
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

        EBPost post = new EBPost(jsPost, "university-city");
        Assert.assertNotEquals(null, post.getId());
        Assert.assertNotEquals(null, post.getCommentCount());
        Assert.assertNotEquals(null, post.getContent());
        Assert.assertNotEquals(null, post.getCoordinate());
        Assert.assertNotEquals(null, post.getPostUrl());
        Assert.assertNotEquals(null, post.getReactionCount());
        Assert.assertNotEquals(null, post.getReactionScore());
        Assert.assertNotEquals(null, post.getSchema());
        Assert.assertNotEquals(null, post.getTitle());
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
                EBPost post = new EBPost(jsPost, "university-city");
                // Check images.
                Assert.assertEquals(2, post.getPhotoSrcs().size());
                Assert.assertEquals("https://everyblock.s3.amazonaws.com/photos/c/c6a92ce9/1878/4a1ff713c9a69da9a1806d0d791a138c-med.jpg",
                        post.getPhotoSrcs().get(0));
                Assert.assertEquals("https://everyblock.s3.amazonaws.com/photos/c/c6a92ce9/1877/a3f48d2e15b1cf4b47a014fbb36ae74c-med.jpg",
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
                EBPost post = new EBPost(jsPost, "university-city");
                post.retrieveComments();
                // Check comments
                Assert.assertEquals(post.getCommentCount(), post.getComments().size());
                EBComment comment1 = post.getComments().get(0);
                Assert.assertEquals(8560, comment1.getId());
                Assert.assertEquals("https://d3nlbyo2tmce3j.cloudfront.net/images/avatars/1/medium.jpg", comment1.getAvatarSrc());
                Assert.assertEquals("Chaoyi Zha", comment1.getUsername());
                Assert.assertEquals("wow, great!", comment1.getContent());

                EBComment comment2 = post.getComments().get(1);
                Assert.assertEquals(8562, comment2.getId());
                Assert.assertEquals("https://s3.amazonaws.com/everyblock/users/222620/medium.jpg", comment2.getAvatarSrc());
                Assert.assertEquals("Bowen Bao", comment2.getUsername());
                Assert.assertEquals("awesome!", comment2.getContent());
            }
        }
    }
}