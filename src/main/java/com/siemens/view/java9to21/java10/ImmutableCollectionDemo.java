package com.siemens.view.java9to21.java10;

import com.github.javafaker.Faker;
import com.siemens.model.Customer;
import com.siemens.model.SavingsAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.siemens.view.java8.functionalinterface.ComparatorDemo.generateIndividuals;
import static com.siemens.view.java8.streams.StreamDemo.generateSavingsAccounts;

public class ImmutableCollectionDemo {
    public static void main(String[] args) {
        // java 10
        List<SavingsAccount> savingsAccountList = List.copyOf(generateSavingsAccounts());
        savingsAccountList.add(new SavingsAccount()); // warning immutable object is modified.
        /*
         * Exception in thread "main" java.lang.UnsupportedOperationException
         * 	at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
         * 	at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:147)
         * 	at com.siemens.view.java9to21.java10.ImmutableCollectionDemo.main(ImmutableCollectionDemo.java:12)
         */

        Map<String, String> map = Map.copyOf(getMap());
        map.put("new", "new");

        Map<Long, String> customerMap = generateIndividuals().stream()
                .collect(Collectors.toMap(Customer::getAccountNumber, Customer::getContactNumber));

        Map<Long, String> immutableMap = Map.copyOf(customerMap);
        immutableMap.put(123456789L, "new");
    }

    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            map.put(faker.name().firstName(), faker.name().firstName());
        }

        return map;
    }
}
