package com.siemens.view;

import com.github.javafaker.Faker;
import com.siemens.model.FullName;
import com.siemens.model.Individual;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.siemens.view.IndividualComparatorDemo.generateAddresses;
import static com.siemens.view.IndividualComparatorDemo.generateRandomGender;

public class BuiltInFunctionalInterfaceDemo {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Individual individual = Individual.builder()
                .accountNumber(faker.number().numberBetween(10000, 1000000))
                .fullName(new FullName(faker.name().firstName(), "", faker.name().lastName()))
                .addresses(generateAddresses())
                .contactNumber(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .gender(generateRandomGender())
                .dateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .build();

        // Function
        Function<Individual, LocalDate> function = (Individual::getDateOfBirth);

        System.out.println("Date Of Birth: " + function.apply(individual));

        BiFunction<Individual, String, Boolean> function2 = ((obj, str) ->
                obj.getPassword().equals(str));

        System.out.println("Password matches: " + function2.apply(individual, individual.getPassword()));
        System.out.println("Password matches: " + function2.apply(individual, ""));
    }
}
