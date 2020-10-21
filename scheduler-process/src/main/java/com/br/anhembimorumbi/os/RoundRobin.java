package com.br.anhembimorumbi.os;

import com.br.anhembimorumbi.os.exceptions.process.RepeatedProcessEnqueueException;
import com.br.anhembimorumbi.os.exceptions.process.IllegalIOTimeException;
import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;

public class RoundRobin implements IScheduler {
    protected IContext context;
    protected long quantum;
    protected IProcessQueue list;
    protected IProcessQueue finished;
    protected IScheduler outputToScheduler;
    public IProcessListener hooks;

    private class Responder implements IProcessListener {
        private IProcessQueue list;
        private IProcessQueue finished;
        private IProcessListener hook;

        public Responder(IProcessQueue p, IProcessQueue finished, IProcessListener hooks) {
            this.list = p;
            this.finished = finished;
            this.hook = hooks;
        }

        private void move() {
            try {
                IProcess denqueue = this.list.denqueue();
                this.list.enqueue(denqueue);
            } catch (RepeatedProcessEnqueueException e) {
                //DO NOTHING
            }
        }

        public void Quantum(IProcess process) {
            move();
            if(this.hook != null) {
                this.hook.Quantum(process);
            }
        }

        public void IO(IProcess process) {
            move();
            if(this.hook != null) {
            this.hook.IO(process);
            }
        }

        public void Close(IProcess process) {
            try {
                IProcess d = this.list.denqueue();
                this.finished.enqueue(d);
            if(this.hook != null) {
                this.hook.Close(process);
            }
            } catch (RepeatedProcessEnqueueException e) {
                //DO NOTHING
            }
        }

        public void Running(IProcess process) {
            //DO NOTHING
        }
    }

    public RoundRobin(IContext c, long quantum) {
        this.context = c;
        this.quantum = quantum;
        this.context.setQuantum(this.quantum);
        this.list = new ProcessQueue();
        this.finished = new ProcessQueue();
    }

    public void setContext(IContext context) {
        this.context = context;
        this.context.setQuantum(this.quantum);
    }

    public IContext getContext() {
        return this.context;
    }

    public void add(IProcess process) throws RepeatedProcessEnqueueException {
        process.hook(new Responder(this.list, this.finished, this.hooks));

        process.setContext(this.context);
        this.list.enqueue(process);
    }

    public IProcess getProcess(long PID) {
        long index = this.list.search(PID);
        if (index == -1) {
            return null;
        }

        return this.list.index(index);
    }

    public IProcessQueue getQueue() {
        return this.list;
    }

    public IProcessQueue getProcessed() {
        return this.finished;
    }

    public long size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public IProcess Run() throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException {
        if (this.list.isEmpty()) {
            return null;
        }

        IProcess current = this.list.peek();
        current.Run();

        return current;
    }

    public void setOutputProcess(IScheduler scheduler) {
        this.outputToScheduler = scheduler;
    }

    public String toString() {
        return null;
    }
}