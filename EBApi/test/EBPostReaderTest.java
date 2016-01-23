import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**

 */
public class EBPostReaderTest {
    @Test
    public void SlugNameTest() {

        //EBPostReader ebPostReader = new EBPostReader();
    }

    @Test
    public void endAtLastPageTest() {
        EBLocation loc = new EBLocation("University City");
        EBPostReader reader = new EBPostReader(loc);
        JSONObject jsLastPage = new JSONObject(EBConn.getJSON(
                EBUtils.getInstance()
                        .getSlugTimelineUrl("university-city") + "&page=32"));
        assertEquals(false, reader.hasNextPage(jsLastPage));
    }

    @Test
    public void getAllPosts() {
        EBLocation loc = new EBLocation("University City");
        EBPostReader reader = new EBPostReader(loc);
        reader.getEBPosts();
        assertEquals(reader.getPostCount(), reader.getEbCompletePostList().size());

        EBPost randPost = reader.getEbCompletePostList().get(200);
        System.out.println(randPost.getTitle());
        System.out.println(randPost.getContent());
        assertNotEquals(null, randPost.getTitle());
        assertNotEquals(null, randPost.getContent());
    }
}