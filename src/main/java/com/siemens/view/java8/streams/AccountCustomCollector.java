package com.siemens.view.java8.streams;

import com.siemens.model.AccountAggregator;
import com.siemens.model.SavingsAccount;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class AccountCustomCollector implements Collector<SavingsAccount, AccountAggregator, AccountAggregator> {
    // instance of account
    @Override
    public Supplier<AccountAggregator> supplier() {
        return AccountAggregator::new;
    }

    @Override
    public BiConsumer<AccountAggregator, SavingsAccount> accumulator() {
        return ((accountAggregator, savingsAccount) -> accountAggregator.setTotalCost(savingsAccount.getRunningTotals().add(accountAggregator.getTotalCost())));
    }

    @Override
    public BinaryOperator<AccountAggregator> combiner() {
        return null;
    }

    @Override
    public Function<AccountAggregator, AccountAggregator> finisher() {
        return (accountAggregator -> accountAggregator);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
