import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**

 */
public class EBPostReaderTest {
    private EBLocation loc = new EBLocation("University City");

    @Test
    public void SlugNameTest() {

        //EBPostReader ebPostReader = new EBPostReader();
    }

    @Test
    public void endAtLastPageTest() {
        EBPostReader reader = new EBPostReader(loc);
        JSONObject jsLastPage = new JSONObject(EBConn.getJSON(
                EBUtils.getInstance()
                        .getSlugTimelineUrl("university-city") + "&page=32"));
        assertEquals(false, reader.hasNextPage(jsLastPage));
    }

    @Test
    public void getAllPostsTest() {
        EBPostReader reader = new EBPostReader(loc);
        reader.retrieveAllEBPosts();
        assertEquals(reader.getPostCount(), reader.getEbCompletePostList().size());

        EBPost randPost = reader.getEbCompletePostList().get(200);
        System.out.println(randPost.getTitle());
        System.out.println(randPost.getContent());
        assertNotEquals(null, randPost.getTitle());
        assertNotEquals(null, randPost.getContent());
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
}