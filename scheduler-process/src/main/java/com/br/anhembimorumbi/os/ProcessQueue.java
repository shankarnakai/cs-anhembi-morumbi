package com.br.anhembimorumbi.os;
import com.br.anhembimorumbi.os.exceptions.process.RepeatedProcessEnqueueException;

public class ProcessQueue implements IProcessQueue {
    protected IProcessNode first;
    protected IProcessNode last;
    protected long size;

    public boolean isEmpty() {
        return (this.first == null);
    }

    public void enqueue(IProcess process) throws RepeatedProcessEnqueueException {
        if (this.isEmpty()) {
            this.first = new ProcessNode(process);
            this.last = this.first;
            this.size += 1;
            return;
        }

        if (this.search(process.PID()) != -1) {
            throw new RepeatedProcessEnqueueException(process.getPID());
        }

        IProcessNode next = new ProcessNode(process);
        this.last.setNext(next);
        this.last = next;
        this.size += 1;
    }

    public IProcess denqueue() {
        if (this.isEmpty()) {
            return null;
        }

        IProcessNode dequeue = this.first;
        this.first = this.first.getNext();
        this.size -= 1;
        return dequeue.getValue();
    }

    public IProcess peek() {
        if(this.first == null) {
            return null;
        }
        return this.first.getValue();
    }

    public long size() {
        return this.size;
    }

    public IProcess index(long index) {
        if (this.isEmpty()) {
            return null;
        }

        if (index < 0 || this.size < index + 1) {
            return null;
        }

        int i = 0;
        IProcessNode next = this.first;

        while (next != null) {
            if (i == index) {
                return next.getValue();
            }
            next = next.getNext();
            i += 1;
        }
        return null;
    }

    public long search(long PID) {
        IProcessNode next = this.first;
        int i = 0;

        while (next != null) {
            if(PID == next.getValue().PID()) {
                return i;
            }
            next = next.getNext();
            i += 1;
        }
        return -1;
    }

    public void sort() {
        
        for (int i= 0; i < this.size;i +=1) {
            IProcessNode next = this.first;
            while (next != null) {
                IProcessNode current = next;
                next = next.getNext();

                if(next != null && current.getValue().getArrived() > next.getValue().getArrived()) {
                IProcess aux = next.getValue();
                next.setValue(current.getValue());
                current.setValue(aux);
                }
            }
        }
    }

    public IProcess[] toArray() {
        if(this.size() <= 0) {
            return null;
        }
        IProcess[] list = new IProcess[(int) this.size];
        int index = 0;
        IProcessNode next = this.first;

        while (next != null) {
            list[index] = next.getValue();
            next = next.getNext();
            index += 1;
        }
        return list;
    }
}