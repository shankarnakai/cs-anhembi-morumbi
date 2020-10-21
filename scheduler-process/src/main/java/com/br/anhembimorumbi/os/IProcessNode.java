package com.br.anhembimorumbi.os;

public interface IProcessNode {
    void setNext(IProcessNode node);
    IProcessNode getNext();
    void setValue(IProcess i);
    IProcess getValue();
}