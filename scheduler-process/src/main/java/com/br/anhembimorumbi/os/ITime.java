package com.br.anhembimorumbi.os;

public interface ITime {
    void set(long i);
    long get();

    void add();
    void add(long number);

    void sub();
    void sub(long number);

    boolean equals(ITime t);
}