package com.siemens.view;

import com.siemens.model.Individual;

import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<Individual> individuals = ComparatorDemo.generateIndividuals();

        individuals.stream()
                .map(Individual::getDateOfBirth)
                .filter(dateOfBirth -> dateOfBirth.getYear() < 2000)
                .forEach(System.out::println);
    }
}
