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
    public void readAllPageTest() {
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
    }
}