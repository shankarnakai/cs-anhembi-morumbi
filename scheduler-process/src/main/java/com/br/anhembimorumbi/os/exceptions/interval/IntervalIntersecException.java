package com.br.anhembimorumbi.os.exceptions.interval;

import com.br.anhembimorumbi.os.IInterval;

/*
 * @author Shankar Nakai Gonçalves dos Santos 
 */
public class IntervalIntersecException extends Exception {
    private IInterval one;
    private IInterval two;

    public IntervalIntersecException(IInterval one, IInterval two) {
        super();
        this.one = one;
        this.two = two;
    }

    public IntervalIntersecException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        if(this.two.getStart() > this.one.getStart()) {
            return String.format("The interval %s is intersec with %s", this.two.toString(), this.one.toString());
        }
        return String.format("The interval %s is intersec with %s", this.one.toString(), this.two.toString());
    }
 }