package com.br.anhembimorumbi.os;

public class IntervalNode implements IIntervalNode {
    private IInterval value;
    private IIntervalNode next;

    public IntervalNode(IInterval value) {
        this.value = value;
    }

    public void setNext(IIntervalNode node){
        this.next = node;
    }

    public IIntervalNode getNext() {
        return this.next;
    }

   public void setValue(IInterval value) {
        this.value = value;
    }

    public IInterval getValue() {
        return this.value;
    }
}