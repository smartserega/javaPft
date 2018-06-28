package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"java", "c#", "Python", "PHP"};


        List<String> languages = Arrays.asList("java", "c#", "Python", "PHP");


        for (int i = 0; i < languages.size(); i++) {
            System.out.println("Я хочу выучить " + languages.get(i));
        }
    }
}
