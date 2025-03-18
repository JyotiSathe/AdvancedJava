package com.siemens.view.java8.functionalinterface;

import com.github.javafaker.Faker;
import com.siemens.facade.DataGenerator;
import com.siemens.model.FullName;
import com.siemens.model.Individual;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.function.*;

import static com.siemens.view.java8.functionalinterface.ComparatorDemo.generateAddresses;
import static com.siemens.view.java8.functionalinterface.ComparatorDemo.generateRandomGender;

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

        // Supplier with constructor reference
        // does not take input but produces the output

        Supplier<Individual> individualSupplier = Individual::new;

        Individual individualObj = individualSupplier.get();

        individualObj.setAccountNumber(faker.number().numberBetween(10000, 1000000));
        System.out.println(individualObj.getAccountNumber());

        // Supplier method reference
        Supplier<String> supplier = DataGenerator::getName;
        System.out.println(supplier.get());

        // Consumer
        // Accepts input returns nothing
        Consumer<List<Individual>> consumer = (list) -> {
            for (Individual individualData : list) {
                System.out.println(individualData);
            }
        };

        consumer.accept(ComparatorDemo.generateIndividuals().subList(0, 10));

        // Predicate
        Predicate<Individual> individualPredicate = (obj) -> obj.getDateOfBirth().isBefore(LocalDate.now());

        System.out.println("Is DOB before current date: " + individualPredicate.test(individual));
    }
}
