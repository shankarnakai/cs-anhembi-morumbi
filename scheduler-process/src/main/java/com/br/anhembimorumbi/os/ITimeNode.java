package com.br.anhembimorumbi.os;

public interface ITimeNode {
    void setNext(ITimeNode node);
    ITimeNode getNext();
    void setValue(long i);
    long getValue();
}