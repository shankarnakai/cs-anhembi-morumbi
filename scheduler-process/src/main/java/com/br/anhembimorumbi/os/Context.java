package com.br.anhembimorumbi.os;

import java.io.Writer;

public class Context implements IContext {
    protected Writer writer;
    protected ITime time;
    protected long quantum;

    public Context(Writer w) {
        this.writer = w;
        this.time = new Time(0);
        this.quantum = 0;
    }

    public Context(Writer w, long quantum) {
        this.writer = w;
        this.time = new Time(0);
        this.quantum = quantum;
    }

    public Context(Writer w, long quantum,  ITime time) {
        this.writer = w;
        this.time = time;
        this.quantum = quantum;
    }

    public void setWriter(Writer w) {
        this.writer = w;
    }

    public void setQuantum(long value) {
        this.quantum = value;
    }

    public Writer getWriter() {
        return this.writer;
    }

    public ITime getClock() {
        return this.time;
    }

    public long getQuantum() {
        return this.quantum;
    }
}