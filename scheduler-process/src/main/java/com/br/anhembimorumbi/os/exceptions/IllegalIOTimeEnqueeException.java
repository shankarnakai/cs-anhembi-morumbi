package com.br.anhembimorumbi.os.exceptions;
import com.br.anhembimorumbi.os.ITimeNode;

/*
 * @author Shankar Nakai Gonçalves dos Santos 
 */
 public class IllegalIOTimeEnqueeException extends Exception {
    private ITimeNode before;
    private ITimeNode time;

    public IllegalIOTimeEnqueeException(ITimeNode time , ITimeNode before) { 
        super();
        this.before = before;
        this.time = time;
    }

    public IllegalIOTimeEnqueeException(Throwable cause) { super(cause); }

    public String getMessage() {
        return String.format("Invalid I/O time (%d), must be bigger than duration the before Time (%d)", this.time.getValue(), this.before.getValue());
    }
 }