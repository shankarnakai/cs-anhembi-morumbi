package com.br.anhembimorumbi.os;

import java.io.Writer;
import java.util.Arrays;

import com.br.anhembimorumbi.os.exceptions.process.IllegalIOTimeException;
import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;

public class Process implements IProcess {
    protected IProcessListener hooks;

    protected IContext context;
    protected long PID;
    protected long duration;
    protected long useTime;
    protected ITimeQueue io;

    protected long arrived;

    protected boolean isRunning = false;
    protected IIntervalPile intervals;

    //CONTROLS THE TIME
    protected long start;
    protected long timeRunning;

    public Process(IContext c, long pid, int duration, long arrived) {
        this.context = c;
        this.io = new TimeQueue();
        this.PID = pid;
        this.duration = duration;
        this.intervals = new IntervalPile();
        this.start = 0;
        this.arrived = arrived;
    }

    public Process(long pid, int duration, long arrived) {
        this(new Context(null), pid, duration, arrived);
    }

    public Process(long pid, int duration) {
        this(new Context(null), pid, duration, 0);
    }

    public long PID() {
        return this.PID;
    }

    public String getPID() {
        return String.format("PID-%03d", this.PID);
    }

    public String getShortPID() {
        return String.format("P%d", this.PID);
    }

    public long getArrived() {
        return this.arrived;
    }

    public long getDuration() {
        return this.duration;
    }

    public long getRemaingDuration() {
        return this.duration - this.useTime;
    }

    public void hook(IProcessListener listener) {
        this.hooks = listener;
    }

    public void fire(EventEnum event) {
        if (this.hooks == null) {
            return;
        }

        switch (event) {
        case CLOSE:
            this.hooks.Close(this);
            break;
        case IO:
            this.hooks.IO(this);
            break;
        case QUANTUM:
            this.hooks.Quantum(this);
            break;
        case RUNNING:
            this.hooks.Running(this);
            break;
        default:
            return;
        }
    }

    //Tell to execute Process and consume 1 second of duration 
    public void Run() throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException {
        if (!this.isRunning) {
            this.start = this.context.getClock().get();
        }

        if (this.getRemaingDuration() <= 0) {
            return;
        }

        this.isRunning = true;
        this.fire(EventEnum.RUNNING);

        this.useTime += 1;
        this.timeRunning += 1;
        if (this.getRemaingDuration() <= 0) {
            this.Stop();
            this.fire(EventEnum.CLOSE);
        } else if (this.context.getQuantum() > 0 && this.timeRunning >= this.context.getQuantum()) {
            this.Stop();
            this.fire(EventEnum.QUANTUM);
        } 

        if (!this.io.isEmpty() && this.useTime == this.io.peek().getValue()) {
            this.Stop();
            this.io.denqueue();
            this.fire(EventEnum.IO);
        }
    }

    public void Stop() throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException {
        if (this.isRunning && this.start > 0) {
            long end = this.context.getClock().get();
            this.intervals.push(new Interval(this.start, end));
            this.start = 0;
        }

        this.timeRunning = 0;
        this.isRunning = false;
    }

    public Boolean isProcessing() {
        return this.isRunning;
    }

    public ITimeQueue getIOTimes() {
        return this.io;
    }

    public void addIOTimes(long time) throws IllegalIOTimeException, IllegalIOTimeEnqueeException {
        if (time > this.duration) {
            throw new IllegalIOTimeException(this.getPID(), time, this.duration);
        }
        this.io.enqueue(new TimeNode(new Time(time)));
    }

    public IInterval[] getIntervals() {
        return this.intervals.toArray();
    }

    public IInterval[] getWaitIntervals() throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException {
        IIntervalPile waitings = new IntervalPile();
        IInterval[] inters = this.intervals.toArray();
        long start = 0;
        long end = 0;
        for(int i = (inters.length - 1); i > 0; i -= 1) {
            start = inters[i].getEnd();
            end = inters[i-1].getStart();
            waitings.push(new Interval(start, end));
        }

        return waitings.toArray();
    }

    public void setContext(IContext c) {
         this.context = c;
    }

    public IContext getContext() {
        return this.context;
    }

    public boolean equals(IProcess proc) {
        boolean compare = this.PID() == proc.PID();
        compare &= this.getDuration() == proc.getDuration();
        compare &= this.getArrived() == proc.getArrived();
        compare &= Arrays.equals(this.getIOTimes().toArray(), proc.getIOTimes().toArray());

        return compare;
    }

    public String toString() {
        return "";
    }
}