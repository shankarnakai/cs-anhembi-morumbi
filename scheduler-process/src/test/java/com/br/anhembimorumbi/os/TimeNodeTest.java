package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import static org.junit.Assert.*;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class TimeNodeTest {
    @Test 
    public void testSetValues() {
        try{
            ITimeNode node = new TimeNode(new Time(5));
            assertEquals("Should value is 5", 5, node.getValue());
            node.setValue(35);
            assertEquals("Should value is 35", 35, node.getValue());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test 
    public void testTimeSetNext() {
        try{
            ITimeNode node = new TimeNode(new Time(5));
            node.setNext(new TimeNode(new Time(35)));
            assertEquals("Should next value is 35", 35, node.getNext().getValue());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}
