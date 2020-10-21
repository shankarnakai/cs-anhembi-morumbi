package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import static org.junit.Assert.*;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class IntervalTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInterval() {
        try {
            Interval interval = new Interval(5, 8);
            assertEquals("Should start on 5", 5, interval.getStart());
            assertEquals("Should end on 8", 8, interval.getEnd());
            assertEquals("Should interval of 5 to 8 equals 3", 3, interval.getInterval());
        } catch (Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testEndBiggerThanBegin() throws IllegalIntervalException {
        thrown.expect(IllegalIntervalException.class);
        thrown.expectMessage("The start time (8) must be lower than end time (5)");
        thrown.reportMissingExceptionWithMessage("IllegalIntervalException expected");
        new Interval(8, 5);
    }

    @Test
    public void testEquals() {
        try {
            Interval interval_1 = new Interval(5, 8);
            Interval interval_2 = new Interval(5, 8);
            assertEquals("Should be equals", true, interval_1.equals(interval_2));

            interval_1 = new Interval(5, 8);
            interval_2 = new Interval(5, 10);
            assertEquals("Should be differente", false, interval_1.equals(interval_2));
        } catch (Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testisAfter() {
        try {
            Interval interval_1 = new Interval(5, 8);
            Interval interval_2 = new Interval(9, 10);
            assertEquals("Should be interval_2 after interval_1", true, interval_1.isAfter(interval_2));
            assertEquals("Should be interval_1 before interval_2 ", false, interval_2.isAfter(interval_1));

            interval_1 = new Interval(5, 8);
            interval_2 = new Interval(6, 10);
            assertEquals("Shloud be not be after, because is intersec", false, interval_1.isAfter(interval_2));
        } catch (Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testIsIntersec() {
        try {
            Interval interval_1 = new Interval(5, 8);
            Interval interval_2 = new Interval(6, 10);
            assertEquals("Should interval_1 intersec interval_2", true, interval_1.isIntersec(interval_2));
            assertEquals("Should interval_2 intersec interval_1", true, interval_2.isIntersec(interval_1));
        } catch (Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}
