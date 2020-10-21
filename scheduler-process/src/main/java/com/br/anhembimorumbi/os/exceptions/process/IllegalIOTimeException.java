package com.br.anhembimorumbi.os.exceptions.process;

/*
 * @author Shankar Nakai Gonçalves dos Santos 
 */
 public class IllegalIOTimeException extends Exception {
     private String PID;
    private long time;
    private long duration;

    public IllegalIOTimeException(String PID, long time, long duration) { 
        super();
        this.PID = PID;
        this.time = time;
        this.duration = duration;
    }

    public IllegalIOTimeException(Throwable cause) { super(cause); }

    public String getMessage() {
        return String.format("%s Invalid I/O time (%d), must be lower than duration (%d)",this.PID, this.time, this.duration);
    }
 }