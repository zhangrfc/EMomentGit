import org.json.JSONArray;
import org.json.JSONObject;

/**

 */
public class EBLocation {
    public final String name;

    private String slugName = null;
    private Boundary boundary;

    public EBLocation(String name) {
        this.name = name;
    }

    public EBLocation(String name, Boundary boundary) {
        this.name = name;
        this.boundary = boundary;
    }

    public boolean contains(Double longitude, Double latitude) {
        return contains(new Point(longitude, latitude));
    }

    public boolean contains(Point point) {
        return boundary.contains(point);
    }

    public String getSlugName() {
        if (slugName == null) {
            retrieveSlugName();
        }
        return slugName;
    }

    public void setSlugName(String slugName) {
        this.slugName = slugName;
    }

    protected void retrieveSlugName() {
        String getNeighborsUrl = EBUtils.getInstance().getNeighborsUrl();
        String jsonStr = EBConn.getJSON(getNeighborsUrl);
        JSONArray arr = new JSONArray(jsonStr);
        for (int i=0; i<arr.length(); ++i) {
            JSONObject listItem = arr.getJSONObject(i);
            if (listItem.getString("name").compareTo(name) == 0) {
                slugName = listItem.getString("slug");
            }
        }
    }
}
