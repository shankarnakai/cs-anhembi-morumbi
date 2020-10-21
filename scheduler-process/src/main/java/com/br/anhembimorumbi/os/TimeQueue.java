package com.br.anhembimorumbi.os;

import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;

public class TimeQueue implements ITimeQueue {
    protected ITimeNode first;
    protected ITimeNode last;
    protected long size;

    public boolean isEmpty() {
        return (this.first == null);
    }

    public void enqueue(ITimeNode time) throws IllegalIOTimeEnqueeException{
        if (this.isEmpty()) {
            this.first = time;
            this.last = this.first;
            this.size += 1;
            return;
        }

        if(time.getValue() < last.getValue()) {
           throw new IllegalIOTimeEnqueeException(time, last); 
        }

        this.last.setNext(time);
        this.last = this.last.getNext(); 
        this.size += 1;
    }

    public ITimeNode denqueue() {
        if (this.isEmpty()) {
            return null;
        }

        ITimeNode dequeue = this.first;
        this.first = this.first.getNext();
        this.size -= 1;
        return dequeue;
    }

    public ITimeNode peek() {
        return this.first;
    }

    public long size() {
        return this.size;
    }

    public long[] toArray() {
        long[] list = new long[(int)this.size];
        int index = 0;
        ITimeNode next = this.first;

        while (next != null) {
            list[index] = next.getValue();
            next = next.getNext();
            index += 1;
        }
        return list;
    }
}