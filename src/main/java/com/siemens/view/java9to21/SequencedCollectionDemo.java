package com.siemens.view.java9to21;

import com.siemens.model.Individual;

import java.util.SequencedCollection;

import static com.siemens.view.java8.functionalinterface.ComparatorDemo.generateIndividuals;

public class SequencedCollectionDemo {

    public static void main(String[] args) {
        SequencedCollection<Individual> sequencedCollection = generateIndividuals();
        System.out.println(sequencedCollection.getFirst());
        System.out.println(sequencedCollection.getLast());
        System.out.println(sequencedCollection.size());

        SequencedCollection<Individual> reversedCollection = sequencedCollection.reversed();
        System.out.println(reversedCollection.getFirst());
        System.out.println(reversedCollection.getLast());
    }
}
