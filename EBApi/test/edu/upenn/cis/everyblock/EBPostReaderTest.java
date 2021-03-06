package edu.upenn.cis.everyblock;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**

 */
public class EBPostReaderTest {
    private EBLocation loc = new EBLocation("University City");

    @Test
    public void SlugNameTest() {

        //edu.upenn.cis.everyblock.EBPostReader ebPostReader = new edu.upenn.cis.everyblock.EBPostReader();
    }

    @Test
    public void endAtLastPageTest() {
        EBPostReader reader = new EBPostReader(loc);
        JSONObject jsLastPage = new JSONObject(
                EBConn.getJSON(EBUtils.getInstance()
                        .getSlugTimelineUrl("university-city") + "&page=32"));
        Assert.assertEquals(false, reader.hasNextPage(jsLastPage));
    }

    @Test
    public void getAllPostsTest() {
        EBPostReader reader = new EBPostReader(loc);
        reader.retrieveAllEBPosts();
        Assert.assertEquals(reader.getPostCount(), reader.getEbCompletePostList().size());

        EBPost randPost = reader.getEbCompletePostList().get(200);
        System.out.println(randPost.getTitle());
        System.out.println(randPost.getContent());
        Assert.assertNotEquals(null, randPost.getTitle());
        Assert.assertNotEquals(null, randPost.getContent());
    }

    @Test
    public void getAllPostsTest2() {
        EBPostReader reader = new EBPostReader(loc);
        reader.retrieveAllEBPostsDB();
        Assert.assertEquals(reader.getPostCount(), reader.getEbCompletePostList().size());

        EBPost randPost = reader.getEbCompletePostList().get(200);
        System.out.println(randPost.getTitle());
        System.out.println(randPost.getContent());
        Assert.assertNotEquals(null, randPost.getTitle());
        Assert.assertNotEquals(null, randPost.getContent());
    }

    @Test
    public void sortedPostTest() {
        EBPostReader reader = new EBPostReader(loc);
        List<EBPost> posts = reader.retrieveSortedEBPosts();
        // test random posts
        assertEquals(true, posts.get(1).getDistance() < posts.get(3).getDistance());
        assertEquals(true, posts.get(3).getDistance() < posts.get(7).getDistance());
        assertEquals(true, posts.get(1).getDistance() < posts.get(7).getDistance());
        assertEquals(true, posts.get(7).getDistance() < posts.get(103).getDistance());
    }

    @Test
    public void refreshLocationTest() {
        // TODO: test refresh location
        // coordinates default in university city
        // start with location as somewhere else
        // after refreshing, location should change.
        EBLocation location = new EBLocation("Allegheny West");
        EBPostReader reader = new EBPostReader(location);
        reader.refreshLocation();
        assertEquals("university-city", reader.getCurLocation().getSlugName());
    }
}