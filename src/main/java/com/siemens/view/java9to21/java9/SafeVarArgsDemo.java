package com.siemens.view.java9to21.java9;

import java.util.Arrays;
import java.util.List;

public class SafeVarArgsDemo {
    public static void main(String[] args) {
        showSkillSets("C#");
        showSkillSets("Java", "C#");
        showSkillSets("Java", "C#", "CPP");

//        foo(List.of("Java", "C#", "CPP"));
    }

    public static <T> void foo(List<T>... bar) {
        // First, strip away the array type (arrays allow this kind of upcasting)
        Object[] objectArray = bar;

        // Next, insert an element with an incorrect type into the array
        objectArray[0] = Arrays.asList(Integer.valueOf(42));

        // Finally, try accessing the original array. A runtime error will occur
        // (ClassCastException due to a casting from Integer to String)
        T firstElement = bar[0].get(0);

        System.out.println(firstElement);
    }

    // java 9
    @SafeVarargs
    private static <T> void showSkillSets(T... skills) {
        Arrays.stream(skills).toList().forEach(System.out::println);
    }
}
