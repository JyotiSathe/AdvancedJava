package com.siemens.view.java9to21;

import com.siemens.model.SavingsAccount;

import java.util.List;

import static com.siemens.view.java8.streams.StreamDemo.generateSavingsAccounts;

public class ImmutableCollectionDemo {
    public static void main(String[] args) {
        List<SavingsAccount> savingsAccountList = List.copyOf(generateSavingsAccounts());
        savingsAccountList.add(new SavingsAccount()); // warning immutable object is modified.
        /*
         * Exception in thread "main" java.lang.UnsupportedOperationException
         * 	at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
         * 	at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:147)
         * 	at com.siemens.view.java9to21.ImmutableCollectionDemo.main(ImmutableCollectionDemo.java:12)
         */
    }
}
