package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import static org.junit.Assert.*;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class IntervalNodeTest {
    @Test 
    public void testSetValues() {
        try{
            Interval interval = new Interval(5,9);
            IntervalNode node = new IntervalNode(interval);
            assertEquals("Should the same", true, interval.equals(node.getValue()) );
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test 
    public void testSetNext() {
        try{
            IInterval interval = new Interval(5,9);
            IntervalNode node = new IntervalNode(interval);
            node.setNext(new IntervalNode(new Interval(1,5)));
            IIntervalNode next = node.getNext();
            assertEquals("Should start be ", 1, next.getValue().getStart());
            assertEquals("Should end be ", 5, next.getValue().getEnd());
            assertEquals("Should next equals", true, next.getValue().equals(new Interval(1,5)));
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}