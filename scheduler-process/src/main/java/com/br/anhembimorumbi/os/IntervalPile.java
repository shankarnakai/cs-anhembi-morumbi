package com.br.anhembimorumbi.os;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;

public class IntervalPile implements IIntervalPile {
    protected IIntervalNode top;
    protected long size;

    public boolean isEmpty() {
        return (this.top == null);
    }

    public IInterval top() {
        return this.top.getValue();
    }

    public void push(IInterval interval) throws IntervalIntersecException, IntervalOutOfOrderException {
        if (this.isEmpty()) {
            this.top = new IntervalNode(interval);
            this.size += 1;
            return;
        }

        IInterval last = this.top.getValue();
        if(!last.isAfter(interval)) {
            if(last.isIntersec(interval)) {
                throw new IntervalIntersecException(last,  interval);
            }
            throw new IntervalOutOfOrderException(interval, last);
        }

        IIntervalNode previous = this.top;
        this.top = new IntervalNode(interval);
        this.top.setNext(previous);
        this.size += 1;
    }

    public IInterval pop() {
        if (this.isEmpty()) {
            return null;
        }

        IIntervalNode pop = this.top;
        this.top = this.top.getNext();
        this.size -= 1;
        return pop.getValue();
    }


    public long size() {
        return this.size;
    }

    public IInterval[] toArray() {
        IInterval[] list = new IInterval[(int)this.size];
        int index = 0;
        IIntervalNode next = this.top;

        while (next != null) {
            list[index] = next.getValue();
            next = next.getNext();
            index += 1;
        }
        return list;
    }
}