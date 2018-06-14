package ru.stqa.pft.sandbox;

public class PointMain {
    public static void main(String[] args) {

        Point p1 = new Point(4,2);
        Point p2 = new Point(1,2);
        System.out.println("Расстояние между точками с координатами: \n X1 = " + p1.x + " Y1 = " + p1.y + "\n X2 = " + p2.x + " Y2 = " + p2.y + " \n равняется: " + p1.distance(p2));
    }
}