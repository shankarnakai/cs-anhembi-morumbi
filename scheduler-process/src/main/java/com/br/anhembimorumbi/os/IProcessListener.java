package com.br.anhembimorumbi.os;

interface IProcessListener {
    void Running(IProcess process);
    void Quantum(IProcess process);
    void IO(IProcess process);
    void Close(IProcess process);
}