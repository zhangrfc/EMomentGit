/**

 */
public class EBLocation {
    public final String name;
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
}
