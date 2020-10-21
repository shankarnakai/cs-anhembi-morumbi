package com.br.anhembimorumbi.os.exceptions.interval;
import com.br.anhembimorumbi.os.IInterval;
/*
 * @author Shankar Nakai Gonçalves dos Santos 
 */
 public class IntervalOutOfOrderException extends Exception {
    private IInterval interval;
    private IInterval last;

    public IntervalOutOfOrderException(IInterval interval, IInterval last) { 
        super();
        this.last = last;
        this.interval = interval;
    }

    public IntervalOutOfOrderException(Throwable cause) { super(cause); }

    public String getMessage() {
        return String.format("Unexpected interval %s,  must insert a interval that begin after %s.", interval.toString(),last.toString());
    }
 }