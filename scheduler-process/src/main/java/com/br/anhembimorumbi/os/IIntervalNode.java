package com.br.anhembimorumbi.os;

public interface IIntervalNode {
    void setNext(IIntervalNode node);
    IIntervalNode getNext();
    void setValue(IInterval value);
    IInterval getValue();
}