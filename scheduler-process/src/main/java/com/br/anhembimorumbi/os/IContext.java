package com.br.anhembimorumbi.os;

import java.io.Writer;

public interface IContext {
    public Writer getWriter();
    public ITime getClock();
    public long getQuantum();

    public void setWriter(Writer w); 
    public void setQuantum(long value); 
}