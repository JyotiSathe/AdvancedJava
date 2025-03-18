package com.siemens.view.java9to21.java16;

import com.github.javafaker.Faker;
import com.siemens.model.Account;
import com.siemens.model.CurrentAccount;
import com.siemens.model.SavingsAccount;

import java.math.BigDecimal;
import java.time.ZoneId;

public class SwitchExpressionDemo {

    public static void main(String[] args) {
        Faker faker = new Faker();
        SavingsAccount savingsAccount = SavingsAccount.builder()
                .runningTotals(BigDecimal.valueOf(faker.number().numberBetween(5000L, 100000000L)))
                .openDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .interestRate(faker.number().numberBetween(1, 100))
                .build();

        CurrentAccount currentAccount = CurrentAccount.builder()
                .runningTotals(BigDecimal.valueOf(faker.number().numberBetween(5000L, 100000000L)))
                .openDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .overdraftLimit(faker.number().numberBetween(100000, 10000000))
                .build();

        System.out.println(getAccountType(savingsAccount));
        System.out.println(getAccountType(currentAccount));
    }

    public static String getAccountType(Account account) {
        return switch (account) {
            case SavingsAccount savingsAccount -> "savingAccount";
            case CurrentAccount currentAccount -> "currentAccount";
            default -> null;
        };
    }
}
