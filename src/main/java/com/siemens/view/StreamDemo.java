package com.siemens.view;

import com.siemens.dto.IndividualDTO;
import com.siemens.model.FullName;
import com.siemens.model.Individual;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        // sorting
        individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() < 2000)
                .sorted(Comparator.comparing(Individual::getDateOfBirth))
                .map(i -> new IndividualDTO(i.getFullName(), i.getDateOfBirth()))
//                .map(individual -> String.format("Name: %s, DateOfBirth: %s", individual.getFullName(), individual.getDateOfBirth()))
                .forEach(System.out::println);

        Map<FullName, LocalDate> map = individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() < 2000)
                .collect(Collectors.toMap(Individual::getFullName, Individual::getDateOfBirth));

        map.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
