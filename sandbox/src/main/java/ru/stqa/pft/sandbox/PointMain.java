package ru.stqa.pft.sandbox;

import static ru.stqa.pft.sandbox.Point.distance;

public class PointMain {
    public static void main(String[] args) {

        Point p1 = new Point();
        Point p2 = new Point();
        System.out.println("Расстояние между точками с координатами: \n X1 = " + p1.x1 + " X2 = " + p2.x2 + "\n Y1 = " + p1.y1 + " Y2 = " + p2.y2 + " \n равняется: " + distance(p1, p2));
    }
}