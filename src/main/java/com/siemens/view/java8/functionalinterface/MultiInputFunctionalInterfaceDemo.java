package com.siemens.view.java8.functionalinterface;

import com.siemens.facade.MultiInputFunctionalInterface;

public class MultiInputFunctionalInterfaceDemo {
    public static void main(String[] args) {
        MultiInputFunctionalInterface<Integer, Integer, Integer, Integer, Integer> average = (a, b, c, d) -> ((a + b + c + d) / 4);

        System.out.println("Average of 10,20,30,40 is : " + average.apply(10, 20, 30, 40));
    }
}
