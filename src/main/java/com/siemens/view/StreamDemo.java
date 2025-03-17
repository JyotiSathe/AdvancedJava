package com.siemens.view;

import com.siemens.model.Individual;

import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<Individual> individuals = ComparatorDemo.generateIndividuals();

        individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() < 2000)
                .map(Individual::getFullName)
                .forEach(System.out::println);

        //count
        System.out.println("Number of people born in/before 1994: " + individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() <= 1994)
                .count());
    }
}
