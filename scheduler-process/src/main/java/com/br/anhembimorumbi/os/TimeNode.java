package com.br.anhembimorumbi.os;

public class TimeNode implements ITimeNode {
    private ITime value;
    private ITimeNode next;

    public TimeNode(ITime value) {
        this.value = value;
    }

    public void setNext(ITimeNode node){
        this.next = node;
    }

    public ITimeNode getNext() {
        return this.next;
    }

    public void setValue(long value) {
        this.value.set(value);
    }

    public long getValue() {
        return this.value.get();
    }
}