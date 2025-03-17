package com.siemens.view;

import com.siemens.dto.IndividualDTO;
import com.siemens.model.FullName;
import com.siemens.model.Gender;
import com.siemens.model.Individual;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        List<Individual> individuals = ComparatorDemo.generateIndividuals().subList(0,20);

        individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() < 2000)
                .map(Individual::getFullName)
                .forEach(System.out::println);

        System.out.println();
        //count
        System.out.println("Number of people born in/before 1994: " + individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() <= 1994)
                .count());

        System.out.println();
        // sorting
        individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() < 2000)
                .sorted(Comparator.comparing(Individual::getDateOfBirth))
                .map(i -> new IndividualDTO(i.getFullName(), i.getDateOfBirth()))
//                .map(individual -> String.format("Name: %s, DateOfBirth: %s", individual.getFullName(), individual.getDateOfBirth()))
                .forEach(System.out::println);

        System.out.println();
        Map<FullName, LocalDate> map = individuals.stream()
                .filter(individual -> individual.getDateOfBirth().getYear() < 2000)
                .collect(Collectors.toMap(Individual::getFullName, Individual::getDateOfBirth));

        map.forEach((key, value) -> System.out.println(key + " " + value));

        // count how many male, female and others
        // grouping
        System.out.println();
        Map<Gender, Long> groupingMap = individuals.stream()
                .collect(Collectors.groupingBy(Individual::getGender, Collectors.counting()));

        groupingMap.forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println();
        // allMatch
        System.out.println(individuals.stream().allMatch(i -> i.getFullName().getFirstName().startsWith("A")));

        // anyMatch
        System.out.println(individuals.stream().anyMatch(i -> i.getFullName().getFirstName().startsWith("A")));

    }
}
