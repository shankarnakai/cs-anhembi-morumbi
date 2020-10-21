package com.br.anhembimorumbi.os;

import com.br.anhembimorumbi.os.exceptions.process.IllegalIOTimeException;
import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;

import java.io.Writer;

interface IProcess {
	//SETTED ON CONSTRUCTOR
	IContext getContext();
    void setContext(IContext c);

	long PID();
	String getPID();
	String getShortPID();

	long getDuration();
	long getArrived();
	long getRemaingDuration();

	//Tell to execute Process and consume 1 second of duration 
	void Run() throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException;

	void Stop() throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException;

	Boolean isProcessing();

	void hook(IProcessListener listener);

	ITimeQueue getIOTimes();

	void addIOTimes(long time) throws IllegalIOTimeException, IllegalIOTimeEnqueeException;

	IInterval[] getIntervals();

	IInterval[] getWaitIntervals()
			throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException;

	String toString();

    boolean equals(IProcess proc);
}