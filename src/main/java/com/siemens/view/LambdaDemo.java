package com.siemens.view;

import com.github.javafaker.Faker;
import com.siemens.facade.DataGenerator;

public class LambdaDemo {

    public static void main(String[] args) {
        DataGenerator otpGenerator = ((min, max) -> (new Faker().number().numberBetween(min, max)));

        System.out.println("Generated OTP:" + otpGenerator.otpGenerator(1000, 9999));
    }
}
