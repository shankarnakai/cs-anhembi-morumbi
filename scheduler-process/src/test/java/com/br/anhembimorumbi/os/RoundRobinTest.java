package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class  RoundRobinTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test 
    public void testAddProcess() {
        try{
            IContext c = new Context(null);
            IScheduler scheduler = new RoundRobin(c, 3);
            scheduler.add(new Process(1, 5));
            scheduler.add(new Process(3, 2));
            assertEquals("Should ", 2, scheduler.size());
        } catch(Exception e) {
            fail("Unexpected exception:" + e);
        }
    }

    @Test
    public void testGetProcess() {
        try{
            IContext c = new Context(null);
            IScheduler scheduler = new RoundRobin(c, 3);
            scheduler.add(new Process(1, 5));
            scheduler.add(new Process(3, 2));
            scheduler.add(new Process(8, 5));
            scheduler.add(new Process(2, 2));
            scheduler.add(new Process(4, 5));
            scheduler.add(new Process(9, 2));

            IProcess find = scheduler.getProcess(9);
            assertEquals("Should searched PID is ", "PID-009", find.getPID());
        } catch(Exception e) {
            fail("Unexpected exception:" + e);
        }
    }

    @Test
    public void testRunWithoutIO() {
        try{
            ITime clock = new Time(0);
            IContext c = new Context(null, 3, clock);
            IScheduler scheduler = new RoundRobin(c, 3);
            scheduler.add(new Process(1, 5));
            scheduler.add(new Process(2, 6));
            int total = 11;

            for (int i = 0; i < total; i += 1) {
                clock.add(1);
                if (clock.get() == 4) {
                    assertEquals("Should size is", 2, scheduler.size());
                    assertEquals("Should size of completed process", 0, scheduler.getProcessed().size());
                    assertEquals("Should first of queue ", 2, scheduler.getQueue().peek().PID());
                    assertEquals("Should PID-001 remaing time  ", 2, scheduler.getProcess(1).getRemaingDuration());
                }

                if (clock.get() == 7) {
                    assertEquals("Should size is", 2, scheduler.size());
                    assertEquals("Should size of completed process", 0, scheduler.getProcessed().size());
                    assertEquals("Should first of queue ", 1, scheduler.getQueue().peek().PID());
                    assertEquals("Should PID-003 remaing time ", 3, scheduler.getProcess(2).getRemaingDuration());
                }

                if (clock.get() == 9) {
                    assertEquals("Should size is", 1, scheduler.size());
                    assertEquals("Should size of completed process", 1, scheduler.getProcessed().size());
                    assertEquals("Should first of queue ", 2, scheduler.getQueue().peek().PID());
                    assertEquals("Should first of queue has remaing time ", 3, scheduler.getQueue().peek().getRemaingDuration());
                    assertEquals("Should not find anymore PID-001 on queue ", null, scheduler.getProcess(1));
                }
                scheduler.Run();
            }
        } catch(Exception e) {
            fail("Unexpected exception:" +e);
        }
    }
}