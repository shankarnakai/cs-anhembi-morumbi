package com.br.anhembimorumbi.os;

import java.io.Writer;
import com.br.anhembimorumbi.os.exceptions.process.RepeatedProcessEnqueueException;
import com.br.anhembimorumbi.os.exceptions.process.IllegalIOTimeException;
import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;

interface IScheduler {
	void setContext(IContext context);
	IContext getContext();

	void add(IProcess process) throws RepeatedProcessEnqueueException;
	IProcess getProcess(long PID);
	IProcessQueue getQueue();
	IProcessQueue getProcessed();

	boolean isEmpty();
	IProcess Run() throws IllegalIntervalException, IntervalIntersecException, IntervalOutOfOrderException;

	//it's possible that the process will send to next scheduler
	void setOutputProcess(IScheduler scheduler);

	long size();

	String toString();
}