package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointMainTests {

    @Test
    public void PointMainTests1 () {
        Point p1 = new Point(4,5);
        Point p2 = new Point(1,2);
        Assert.assertEquals(p1.distance(p2), 4.242640687119285);
        System.out.println("\nТест № 1\n\n Расстояние между точками с координатами: \n X1 = " + p1.x + " X2 = " + p2.x + "\n Y1 = " + p1.y + " Y2 = " + p2.y + " \n равняется: " + p1.distance(p2));
    }

    @Test
    public void PointMainTests2 () {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2), 0.0);
        System.out.println("\nТест № 2\n\n Расстояние между точками с координатами: \n X1 = " + p1.x + " X2 = " + p2.x + "\n Y1 = " + p1.y + " Y2 = " + p2.y + " \n равняется: " + p1.distance(p2));
    }

    @Test
    public void PointMainTests3 () {
        Point p1 = new Point(5,5);
        Point p2 = new Point(5,5);
        Assert.assertEquals(p1.distance(p2), 0.0);
        System.out.println("\nТест № 3\n\n Расстояние между точками с координатами: \n X1 = " + p1.x + " X2 = " + p2.x + "\n Y1 = " + p1.y + " Y2 = " + p2.y + " \n равняется: " + p1.distance(p2));
    }


    @Test
    public void PointMainTests4 () {
        Point p1 = new Point(-4,-2);
        Point p2 = new Point(-3,-1);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
        System.out.println("\nТест № 4\n\n Расстояние между точками с координатами: \n X1 = " + p1.x + " X2 = " + p2.x + "\n Y1 = " + p1.y + " Y2 = " + p2.y + " \n равняется: " + p1.distance(p2));
    }

    @Test
    public void PointMainTests5 () {
        Point p1 = new Point(6545612,256462);
        Point p2 = new Point(3981563,123165);
        Assert.assertEquals(p1.distance(p2), 2567511.512069615);
        System.out.println("\nТест № 4\n\n Расстояние между точками с координатами: \n X1 = " + p1.x + " X2 = " + p2.x + "\n Y1 = " + p1.y + " Y2 = " + p2.y + " \n равняется: " + p1.distance(p2));
    }



}



