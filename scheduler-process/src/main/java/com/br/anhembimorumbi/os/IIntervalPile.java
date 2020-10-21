package com.br.anhembimorumbi.os;

import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;

public interface IIntervalPile {
    boolean isEmpty();
    void push(IInterval interval) throws IntervalIntersecException, IntervalOutOfOrderException;
    IInterval pop();
    IInterval top();
    long size();
    IInterval[] toArray();
 } 