package com.siemens.view.java8.functionalinterface;

import com.siemens.facade.TriFunction;

public class TriFunctionDemo {

    public static void main(String[] args) {
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
