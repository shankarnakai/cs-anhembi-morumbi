package com.br.anhembimorumbi.os;

public interface IInterval {
	long getStart();
	long getEnd();
	long getInterval();
	boolean equals(IInterval interval);
	boolean isAfter(IInterval interval); 
	boolean isIntersec(IInterval interval);
	String toString();
}
