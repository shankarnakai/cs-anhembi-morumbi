package com.br.anhembimorumbi.os;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;

/*
 * @author Shankar Nakai Gonçalves dos Santos 
 */
 public class Interval implements IInterval {
	private long start;
	private long end;

	/**
	 * 
	 * */
	public Interval(long start,long end) throws IllegalIntervalException {
		if(end < start) {
			throw new IllegalIntervalException(start, end); 	
		}
		this.start = start;	
		this.end = end;	
	}

	/**
	 * 
	 * */
	public long getStart() {
		return this.start;	
	} 

	/**
	 * 
	 * */
	public long getEnd() {
		return this.end;	
	}

	/**
	 * 
	 * */
	public long getInterval() {
		return this.end - this.start;	
	}

	public boolean equals(IInterval interval) {
		boolean ok = false;
		ok = (interval.getStart() == this.getStart()); 
		ok &= (interval.getEnd() == this.getEnd()); 
		return ok;
	}

	public boolean isAfter(IInterval interval) {
		return (interval.getStart() > this.getEnd()); 
	}

	public boolean isIntersec(IInterval interval) {
		return (
			interval.getStart() > this.getStart() &&
			interval.getStart() < this.getEnd() 
		) || (
			interval.getEnd() > this.getStart() &&
			interval.getEnd() < this.getEnd() 
		); 
	}

	public String toString() {
		return String.format("(%d, %d)", this.start, this.end);
	}
}
