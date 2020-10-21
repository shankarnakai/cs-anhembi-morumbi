package com.br.anhembimorumbi.os;

import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
public interface ITimeQueue {
    boolean isEmpty();

    void enqueue(ITimeNode time) throws IllegalIOTimeEnqueeException;

    ITimeNode denqueue();

    ITimeNode peek();

    long size();
    long[] toArray();
 } 