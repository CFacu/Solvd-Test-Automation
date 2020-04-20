package com.facundo.bank.lambdas;

@FunctionalInterface
public interface ISearch<T> {
    Boolean searchObject(T object);
}