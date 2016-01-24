package edu.upenn.cis.everyblock;

/**
 * Created by bowen on 1/23/16.
 */
public class Point {
    public final double x;
    public final double y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double sqrDistTo(Point point) {
        double xDis = x - point.x;
        double yDis = y - point.y;
        return xDis * xDis + yDis * yDis;
    }
}
