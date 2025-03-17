package com.siemens.view;

import com.siemens.facade.MultiInputFunctionalInterface;
import com.siemens.facade.TriFunction;

public class MultiInputFunctionalInterfaceDemo {
    public static void main(String[] args) {
        MultiInputFunctionalInterface<Integer, Integer, Integer, Integer, Integer> average = (a, b, c, d) -> ((a + b + c + d) / 4);

        System.out.println("Average of 10,20,30,40 is : " + average.apply(10, 20, 30, 40));

        TriFunction<String, String, String, Boolean> triFunction = (obj1, obj2, obj3) -> {
            if (obj1.toLowerCase().equals(obj2)) {
                return obj2.toLowerCase().equals(obj3);
            } else
                return false;

        };

        System.out.println(triFunction.apply("one", "two", "three"));
        System.out.println(triFunction.apply("one", "one", "one"));
    }
}
