package com.siemens.facade;

@FunctionalInterface
public interface DataGenerator {
    int otpGenerator(int min, int max);
}
