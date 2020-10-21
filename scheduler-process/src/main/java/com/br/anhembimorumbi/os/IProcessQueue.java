package com.br.anhembimorumbi.os;
import com.br.anhembimorumbi.os.exceptions.process.RepeatedProcessEnqueueException;
public interface IProcessQueue {
    boolean isEmpty();

    void enqueue(IProcess time) throws RepeatedProcessEnqueueException;
    IProcess denqueue();
    IProcess peek();

    IProcess index(long i);
    long search(long pid);
    void sort();

    long size();
    IProcess[] toArray();
 } 