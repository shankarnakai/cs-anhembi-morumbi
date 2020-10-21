package com.br.anhembimorumbi.os;

public class ProcessNode implements IProcessNode {
    private IProcess value;
    private IProcessNode next;

    public ProcessNode(IProcess value) {
        this.value = value;
    }

    public void setNext(IProcessNode node){
        this.next = node;
    }

    public IProcessNode getNext() {
        return this.next;
    }

    public void setValue(IProcess value) {
        this.value = value;
    }

    public IProcess getValue() {
        return this.value;
    }
}