package com.br.anhembimorumbi.os.exceptions.interval;

/*
 * @author Shankar Nakai Gonçalves dos Santos 
 */
 public class IllegalIntervalException extends Exception {
    private long start;
    private long end;

    public IllegalIntervalException(long start , long end) { 
        super();
        this.start = start;
        this.end = end;
    }

    public IllegalIntervalException(Throwable cause) { super(cause); }

    public String getMessage() {
        return String.format("The start time (%d) must be lower than end time (%d)", this.start, this.end);
    }
 }