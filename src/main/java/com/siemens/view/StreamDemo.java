package com.siemens.view;

import com.github.javafaker.Faker;
import com.siemens.dto.IndividualDTO;
import com.siemens.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        List<Individual> individuals = ComparatorDemo.generateIndividuals().subList(0, 20);

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

        // findFirst
        System.out.println();
        Optional<Individual> individualOptional = individuals.stream()
                .filter(i -> i.getDateOfBirth().getYear() == 1994)
                .findFirst();

        if (individualOptional.isPresent()) {
            System.out.println(individualOptional.get());
        } else {
            System.out.println("No Records for findFirst.");
        }

        // findAny
        System.out.println();
        Optional<Individual> object = individuals.stream()
                .filter(i -> i.getDateOfBirth().getYear() == 1994)
                .findAny();
//                .orElseThrow(() -> new RuntimeException("No Records for findAny."));
//        System.out.println(object);

        System.out.println();
        // limit
        individuals.stream()
                .filter(i -> i.getDateOfBirth().getYear() < 1994)
                .limit(20)
                .map(i -> new IndividualDTO(i.getFullName(), i.getDateOfBirth()))
                .forEach(System.out::println);

        System.out.println();
        // skip
        individuals.stream()
                .filter(i -> i.getDateOfBirth().getYear() < 1994)
                .skip(5)
                .map(i -> new IndividualDTO(i.getFullName(), i.getDateOfBirth()))
                .forEach(System.out::println);

        // flatten the list
        System.out.println();
        List<Developer> developers = getDevelopers();

        List<Skill> skills = developers.stream()
                .map(Developer::getSkills)
                .flatMap(List::stream)
                .distinct()
                .toList();

        skills.forEach(System.out::println);
    }

    public static List<Developer> getDevelopers() {
        List<Developer> developers = new ArrayList<>();
        Developer developer;
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            developer = new Developer();
            developer.setName(faker.name().firstName());
            developer.setSkills(getSkills());
            developers.add(developer);
        }

        return developers;
    }

    public static List<Skill> getSkills() {
        List<Skill> skills = new ArrayList<>();

        for (int i = 0; i < new Random().nextInt(2, 5); i++) {
            skills.add(generateRandomSkills());
        }
        return skills;
    }

    public static Skill generateRandomSkills() {
        Skill[] values = Skill.values();
        return values[(int) (Math.random() * values.length)];
    }
}
