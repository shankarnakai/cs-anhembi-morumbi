package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class TimeQueueTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test 
    public void testEnquee() {
        try{
            TimeQueue queue = new TimeQueue();
            queue.enqueue(new TimeNode(new Time(5)));
            assertEquals("Should has size equals 1", 1, queue.size());
            assertEquals("Should has in the first position the value 5", 5, queue.peek().getValue());

            queue.enqueue(new TimeNode(new Time(6)));
            queue.enqueue(new TimeNode(new Time(9)));

            assertEquals("Should has size equals 3", 3, queue.size());
            assertEquals("Should has in the first position the value 5", 5, queue.peek().getValue());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testEnqueeTimeLowerThanBefore() throws IllegalIOTimeEnqueeException {
        thrown.expect(IllegalIOTimeEnqueeException.class);
        thrown.expectMessage("Invalid I/O time (3), must be bigger than duration the before Time (5)");
        thrown.reportMissingExceptionWithMessage("IllegalIOTimeEnqueeException expected");

        TimeQueue queue = new TimeQueue();
        queue.enqueue(new TimeNode(new Time(5)));
        queue.enqueue(new TimeNode(new Time(3)));
    }

    @Test 
    public void testDequee() {
       long[] expected  = new long[]{5,6,9};
        try{
            TimeQueue queue = new TimeQueue();
            for(int i = 0; i < expected.length; i += 1) {
                queue.enqueue(new TimeNode(new Time(expected[i])));
            }

            assertEquals(String.format("Should has size equals %d", expected.length), expected.length, queue.size());
            assertEquals(String.format("Should has in the first position the value %d", expected[0]), expected[0], queue.peek().getValue());

            for(int i = 0; i < expected.length; i += 1) {
                //then i dequeue one time
                ITimeNode time = queue.denqueue();
                assertEquals(String.format("Should the dequeue value equals %d",expected[i]), expected[i], time.getValue());
                assertEquals(String.format("Should get size equals %d",expected.length), (expected.length-1) - i, queue.size());
                if (i < (expected.length - 1)) {
                    assertEquals(String.format("Should get in the peek method value %d", expected[i+1]), expected[i+1], queue.peek().getValue());
                } else {
                    assertEquals("Should be empty", true, queue.isEmpty());
                }
            }

            assertEquals("Should be empty", true, queue.isEmpty());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testToArray() {
        try{
            long[] expected = new long[]{5,6,9};
            TimeQueue queue = new TimeQueue();
            queue.enqueue(new TimeNode(new Time(5)));
            queue.enqueue(new TimeNode(new Time(6)));
            queue.enqueue(new TimeNode(new Time(9)));

            assertEquals("Should has size equals 3", expected.length, queue.size());
            assertArrayEquals(String.format("Should has queue is equals %b", expected), expected , queue.toArray() ); 
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}