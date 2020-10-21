package com.br.anhembimorumbi.os.exceptions.process;

/*
 * @author Shankar Nakai Gonçalves dos Santos 
 */
 public class RepeatedProcessEnqueueException extends Exception {
     private String PID;

    public RepeatedProcessEnqueueException(String PID) { 
        super();
        this.PID = PID;
    }

    public RepeatedProcessEnqueueException(Throwable cause) { super(cause); }

    public String getMessage() {
        return String.format("The PID \"%s\" is alredy in queue, it not allowed repeat PID number",this.PID);
    }
 }