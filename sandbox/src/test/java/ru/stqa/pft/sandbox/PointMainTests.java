package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointMainTests {

    @Test
    public void PointMainTests () {
        Point p1 = new Point();
        Point p2 = new Point();
        p1.distance(p1, p2);

        Assert.assertEquals( p1.distance(p1, p2), 2.23606797749979 );

    }

}



