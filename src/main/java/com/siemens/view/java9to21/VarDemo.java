package com.siemens.view.java9to21;

import com.siemens.model.Individual;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.function.Function;

import static com.siemens.view.java8.functionalinterface.ComparatorDemo.generateIndividuals;

public class VarDemo {
    public static void main(String[] args) {
        //type inference
        var name = "jyoti";
        System.out.println(name.getClass().getSimpleName());
        var salary = 46796.78;
        Object object = salary;
        System.out.println(object.getClass().getSimpleName());

        Function<Individual, LocalDate> function = (var obj) -> {
            return obj.getDateOfBirth();
        };
        System.out.println(function.apply(
                generateIndividuals().get(0)
        ));

        generateIndividuals().stream()
                .map((@NonNull var x) ->
                        x.getFullName().getFirstName().toLowerCase())
                .forEach(System.out::println);
    }
}
