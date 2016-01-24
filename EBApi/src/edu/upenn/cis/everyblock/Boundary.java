package edu.upenn.cis.everyblock;

import org.json.JSONArray;

/**
 */
public class Boundary {
    private final Point[] points; // Points making up the boundary

    public Boundary() {
        points = new Point[0];
    }

    public Boundary(JSONArray coordinates) {
        int count = coordinates.length();
        points = new Point[count];
        for (int i=0; i<count; ++i) {
            JSONArray pointJS = coordinates.getJSONArray(i);
            points[i] = new Point(pointJS.getDouble(0), pointJS.getDouble(1));
            System.out.println("edu.upenn.cis.everyblock.Point: x: " + points[i].x + " y: " + points[i].y);
        }
    }

    /**
     * Return true if the given point is contained inside the boundary.
     * See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     * @param test The point to check
     * @return true if the point is inside the boundary, false otherwise
     *
     */
    public boolean contains(Point test) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
            if ((points[i].y > test.y) != (points[j].y > test.y) &&
                    (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y-points[i].y) + points[i].x)) {
                result = !result;
            }
        }
        return result;
    }

    public String getJSON() {
        StringBuilder sb = new StringBuilder();
        if (points.length > 0) {
            sb.append("\"boundary\":[");
            boolean first = true;
            for (Point point : points) {
                if (!first) sb.append(",");
                else first = false;
                sb.append("[")
                        .append(point.x)
                        .append(",")
                        .append(point.y)
                        .append("]");
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
