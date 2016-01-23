import org.json.JSONObject;
import org.jsoup.nodes.Element;

/**

 */
public class EBComment {
    private int id;
    private String avatarSrc;
    private String username;
    private String content;

    public EBComment(Element elem) {
        init(elem);
    }

    public void init(Element elem) {
        id = Integer.parseInt(elem.attributes().get("data-comment-id"));
        avatarSrc = elem.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("src");
        username = elem.select("div.who > a").text();
        content = elem.select("div.text > p").text();
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }
}
