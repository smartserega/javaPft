package ru.stqa.pft.sandbox;

public class Point {

    public double x1 = 1;
    public double y1 = 2;
    public double x2 = 2;
    public double y2 = 4;

    public static double distance(Point p1, Point p2) {

        return Math.sqrt((p2.x2 - p1.x1) * (p2.x2 - p1.x1) + (p2.y2 - p1.y1) * (p2.y2 - p1.y1));
    }
}
