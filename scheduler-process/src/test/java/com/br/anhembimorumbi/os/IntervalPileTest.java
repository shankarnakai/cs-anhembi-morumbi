package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;


/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class IntervalPileTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test 
    public void testValidPush() {
        IIntervalPile pile = new IntervalPile();
        try {
            pile.push(new Interval(1,4));
            assertEquals("Should has 1 items", 1, pile.size());
            assertEquals("Should top equals",true,  pile.top().equals(new Interval(1,4)));
        } catch(Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test 
    public void testIntervalIntersectPush() throws IntervalIntersecException, IntervalOutOfOrderException, IllegalIntervalException {
        thrown.expect(IntervalIntersecException.class);
        thrown.expectMessage("The interval (2, 7) is intersec with (1, 4)");
        thrown.reportMissingExceptionWithMessage("IntervalIntersecException expected");

        IIntervalPile pile = new IntervalPile();
        pile.push(new Interval(1, 4));
        assertEquals("Should has 1 items", 1, pile.size());
        assertEquals("Should top equals", true, pile.top().equals(new Interval(1, 4)));

        pile.push(new Interval(2, 7));
    }

    @Test 
    public void testIntervalWithValueBeforePush() throws IntervalIntersecException, IntervalOutOfOrderException, IllegalIntervalException {
        thrown.expect(IntervalOutOfOrderException.class);
        thrown.expectMessage("Unexpected interval (1, 4),  must insert a interval that begin after (5, 7).");
        thrown.reportMissingExceptionWithMessage("IntervalOutOfOrderException expected");

        IIntervalPile pile = new IntervalPile();
        pile.push(new Interval(5, 7));
        assertEquals("Should has 1 items", 1, pile.size());
        assertEquals("Should top equals", true, pile.top().equals(new Interval(5, 7)));

        pile.push(new Interval(1, 4));
    }

    @Test 
    public void testPop() {
        IIntervalPile pile = new IntervalPile();
        try {
            pile.push(new Interval(1,4));
            pile.push(new Interval(5,7));
            pile.push(new Interval(8,10));
            assertEquals("Should has 3 items", 3, pile.size());
            assertEquals("Should top equals",true,  pile.top().equals(new Interval(8,10)));

            pile.pop();
            assertEquals("Should has 2 items", 2, pile.size());
            assertEquals("Should top equals",true,  pile.top().equals(new Interval(5, 7)));
        } catch(Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testToArray() {
    }
}