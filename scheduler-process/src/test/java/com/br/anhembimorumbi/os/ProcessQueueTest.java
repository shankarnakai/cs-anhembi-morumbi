package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import com.br.anhembimorumbi.os.exceptions.process.RepeatedProcessEnqueueException;
/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class  ProcessQueueTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test 
    public void testEnquee() {
        try{
            ProcessQueue queue = new ProcessQueue();
            queue.enqueue(new Process(5, 10));
            assertEquals("Should has size equals 1", 1, queue.size());
            assertEquals("Should has in the first position the value PID-005", "PID-005", queue.peek().getPID());

            queue.enqueue(new Process(6, 10));
            queue.enqueue(new Process(7, 10));

            assertEquals("Should has size equals 3", 3, queue.size());
            assertEquals("Should has in the first position the value PID-005", "PID-005", queue.peek().getPID());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testEnqueeProcessRepeatedPID() throws RepeatedProcessEnqueueException {
        thrown.expect(RepeatedProcessEnqueueException.class);
        thrown.expectMessage(String.format("The PID \"PID-005\" is alredy in queue, it not allowed repeat PID number"));
        thrown.reportMissingExceptionWithMessage("RepeatedProcessEnqueueException expected");
        ProcessQueue queue = new ProcessQueue();
        queue.enqueue(new Process(5, 10));
        queue.enqueue(new Process(5, 2));
    }

    @Test 
    public void testDequee() {
       String[] expected  = new String[]{"PID-005","PID-006","PID-009"};
       long[] inputPID  = new long[]{5,6,9};
        try{
            ProcessQueue queue = new ProcessQueue();
            for(int i = 0; i < inputPID.length; i += 1) {
                queue.enqueue(new Process(inputPID[i], 10));
            }

            assertEquals(String.format("Should has size equals %d", expected.length), expected.length, queue.size());
            assertEquals(String.format("Should has in the first position the value %s", expected[0]), expected[0], queue.peek().getPID());

            for(int i = 0; i < expected.length; i += 1) {
                IProcess process = queue.denqueue();
                assertEquals(String.format("Should the dequeue value equals %s",expected[i]), expected[i], process.getPID());
                assertEquals(String.format("Should get size equals %d",expected.length), (expected.length-1) - i, queue.size());
                if (i < (expected.length - 1)) {
                    assertEquals(String.format("Should get in the peek method value %s", expected[i+1]), expected[i+1], queue.peek().getPID());
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
    public void testSort() {
        try{
            String[] expected = new String[]{"PID-005","PID-009","PID-006"};
            ProcessQueue queue = new ProcessQueue();
            queue.enqueue(new Process(5,3, 1));
            queue.enqueue(new Process(6,4, 5));
            queue.enqueue(new Process(9,5, 2));

            queue.sort();
            IProcess[] result = queue.toArray();
            if(result.length != expected.length) {
                fail(String.format("Should has size equals %d", expected.length));
            } else {
                for(int i = 0; i < expected.length; i+=1) {
                    assertEquals(String.format("Should has queue is equals %b", expected[i]), expected[i], result[i].getPID()); 
                }
            }
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testToArray() {
        try{
            String[] expected = new String[]{"PID-005","PID-006","PID-009"};
            ProcessQueue queue = new ProcessQueue();
            queue.enqueue(new Process(5, 10));
            queue.enqueue(new Process(6, 10));
            queue.enqueue(new Process(9, 10));

            IProcess[] result = queue.toArray();
            if(result.length != expected.length) {
                fail(String.format("Should has size equals %d", expected.length));
            } else {
                for(int i = 0; i < expected.length; i+=1) {
                    assertEquals(String.format("Should has queue is equals %b", expected[i]), expected[i], result[i].getPID()); 
                }
            }
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}