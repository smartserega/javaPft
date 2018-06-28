package ru.stqa.pft.sandbox;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"java", "c#", "Python", "PHP"};


        for (String l : langs) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
