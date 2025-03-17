package com.siemens.facade;

@FunctionalInterface
public interface MultiInputFunctionalInterface<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);
}
