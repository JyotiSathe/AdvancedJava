package com.siemens.facade;

import com.github.javafaker.Faker;

@FunctionalInterface
public interface DataGenerator {
    int otpGenerator(int min, int max);

    static String getName() {
        return new Faker().name().firstName();
    }
}
