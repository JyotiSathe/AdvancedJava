package com.siemens.view.java8.functionalinterface;

import com.github.javafaker.Faker;
import com.siemens.model.Address;
import com.siemens.model.FullName;
import com.siemens.model.Gender;
import com.siemens.model.Individual;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {

    public static void main(String[] args) {

//        Comparator<Individual> comparator=(o1, o2) -> {
//            return o1.getFullName().getFirstName().compareTo(o2.getFullName().getFirstName());
//        };

        Comparator<Individual> comparator= Comparator.comparing(o -> o.getFullName().getFirstName());

        List<Individual> individuals = generateIndividuals();
        individuals.sort(comparator);

        for (Individual individual : individuals) {
            System.out.println(individual);
        }
    }

    public static List<Individual> generateIndividuals() {
        List<Individual> individuals = new ArrayList<>();
        Individual individual;
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            individual = Individual.builder()
                    .accountNumber(faker.number().numberBetween(10000, 1000000))
                    .fullName(new FullName(faker.name().firstName(), "", faker.name().lastName()))
                    .addresses(generateAddresses())
                    .contactNumber(faker.phoneNumber().cellPhone())
                    .email(faker.internet().emailAddress())
                    .password(faker.internet().password())
                    .gender(generateRandomGender())
                    .dateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .build();
            individuals.add(individual);
        }

        return individuals;
    }

    static List<Address> generateAddresses() {
        List<Address> addresses = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 2; i++) {
            addresses.add(new Address(
                    faker.number().numberBetween(1000, 1000000),
                    faker.address().streetAddressNumber(),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().state(),
                    faker.address().zipCode(),
                    faker.address().country()
            ));
        }
        return addresses;
    }

    public static Gender generateRandomGender() {
        Gender[] values = Gender.values();
        return values[(int) (Math.random() * values.length)];
    }

}
