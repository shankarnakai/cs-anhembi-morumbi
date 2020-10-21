package com.br.anhembimorumbi.os;

public class Time implements ITime {
    private long value;

    public Time(long value) {
        this.value = value;
    }

    public void set(long i) {
        this.value = i;
    }

    public long get() {
        return this.value;
    }

    public void add() {
        this.value += 1;
    }

    public void add(long number) {
        this.value += number;
    }

    public void sub() {
        this.value -= 1;
    }

    public void sub(long number) {
        this.value -= number;
    }


    public boolean equals(ITime t) {
        return this.value == t.get();
    }
}