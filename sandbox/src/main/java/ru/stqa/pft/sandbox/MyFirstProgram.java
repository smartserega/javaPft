package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("Word");
        hello("User");
        hello("Sergey");


        Square s = new Square(5);
        System.out.println("\nПлощадь квадрата со стороной "+s.l + " равна " + s.area());


        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами "+ r.a + " и " + r.b + " равна " + r.area() );


        Point p1 = new Point();
        Point p2 = new Point();
        p1.coordinates1(4,4);
        p2.coordinates2(2,2);


        System.out.println("\n\nРасстояние между точками с координатами: \n X1 = " +p1.x1 +  " X2 = " + p2.x2 + "\n Y1 = " +p1.y1 + " Y2 = " + p2.y2 +" \n равняется: " + Math.sqrt((p2.x2-p1.x1)*(p2.x2-p1.x1) + (p2.y2-p1.y1)*(p2.y2-p1.y1)));


    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }





}