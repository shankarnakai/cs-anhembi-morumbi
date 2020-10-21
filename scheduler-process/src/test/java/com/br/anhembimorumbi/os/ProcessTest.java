package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.br.anhembimorumbi.os.exceptions.process.IllegalIOTimeException;
import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;

import static org.junit.Assert.*;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class ProcessTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testProcessInstanciation() {
        try {
            Process process = new Process(9, 10);

            process.addIOTimes(2);
            process.addIOTimes(4);
            process.addIOTimes(6);
            process.addIOTimes(8);

            assertArrayEquals("Should has I/O times [2,4,6,8]", new long[] { 2, 4, 6, 8 },
                    process.getIOTimes().toArray());
            assertEquals("Should PDI is PID-009", "PID-009", process.getPID());
            assertEquals("Should the duration is 10", 10, process.getDuration());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testProcessIOBiggerThanDuration() throws IllegalIOTimeException, IllegalIOTimeEnqueeException {
        thrown.expect(IllegalIOTimeException.class);
        thrown.expectMessage("PID-001 Invalid I/O time (50), must be lower than duration (10)");
        thrown.reportMissingExceptionWithMessage("IllegalIOTimeException expected");

        Process process = new Process(1, 10);
        process.addIOTimes(50);
    }

    @Test
    public void testRunProcessWithoutIO() {
        try {
            Process proc = new Process(1, 2);
            //SET the scenarie
            assertEquals("Should the PID equals \"PID-001\"", "PID-001", proc.getPID());
            assertEquals("Should the Duration 2", 2, proc.getDuration());

            //FIRST RUN
            proc.Run();
            assertEquals("Should Processing is true", true, proc.isProcessing());
            assertEquals("Should the remaining 1", 1, proc.getRemaingDuration());

            //SECOND RUN
            proc.Run();
            assertEquals("Should Processing is true", false, proc.isProcessing());
            assertEquals("Should the remaining 0", 0, proc.getRemaingDuration());

            //DONT SHOULD RUN ANYMORE
            proc.Run();
            assertEquals("Should Processing is false", false, proc.isProcessing());
            assertEquals("Should the remaining 0", 0, proc.getRemaingDuration());
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }
    }

    @Test
    public void testRunProcess() {
        ITime clock = new Time(0);
        IContext c = new Context(null,3, clock);
        try {
            Process proc = new Process(c, 1, 5, 0);
            proc.addIOTimes(2);
            proc.addIOTimes(5);

            //SET the scenarie
            assertEquals("Should the PID equals \"PID-001\"", "PID-001", proc.getPID());
            assertEquals("Should the Duration 5", 5, proc.getDuration());

            clock.add();
            //FIRST RUN
            proc.Run();
            assertEquals("Should Processing is true", true, proc.isProcessing());
            assertEquals("Should the remaining 4", 4, proc.getRemaingDuration());

            //SECOND RUN
            clock.add();
            proc.Run();
            assertEquals("Should Processing is false", false, proc.isProcessing());
            assertEquals("Should the remaining 3", 3, proc.getRemaingDuration());

            //ThIRD RUN
            clock.add();
            proc.Run();
            assertEquals("Should Processing is false", true, proc.isProcessing());
            assertEquals("Should the remaining 2", 2, proc.getRemaingDuration());

            //FORTH RUN
            clock.add();
            proc.Run();
            assertEquals("Should Processing is false", true, proc.isProcessing());
            assertEquals("Should the remaining 1", 1, proc.getRemaingDuration());

            //FIVETH RUN
            clock.add();
            proc.Run();
            assertEquals("Should Processing is false", false, proc.isProcessing());
            assertEquals("Should the remaining 0", 0, proc.getRemaingDuration());
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }
    }

    @Test
    public void testIntervalsWithoutIO() {
        Time clock = new Time(0);
        IContext c = new Context(null, 3, clock);
        Process proc = new Process(c,1, 10, 0);

        IInterval[] expected = new Interval[4];
        IInterval[] expectedWaiting = new Interval[3];

        try {
            expected[3] = new Interval(1,3);
            expected[2] = new Interval(4,6);
            expected[1] = new Interval(7,9);
            expected[0] = new Interval(10,10);

            expectedWaiting[2] = new Interval(3,4);
            expectedWaiting[1] = new Interval(6,7);
            expectedWaiting[0] = new Interval(9,10);

            for( int i = 0; i < proc.getDuration(); i+=1) {
                clock.add(1);
                proc.Run();
            }

            if(proc.isProcessing()) {
                proc.Stop();
            }

            IInterval[] result = proc.getIntervals();
            if(result != null) {
                if (expected.length != result.length) {
                    assertEquals(String.format("Should be %d interval", expected.length), expected.length, result.length );
                } else {
                    for( int i = 0; i < expected.length;i ++) {
                        assertEquals(String.format("Should be interval expected %s equals to result %s in run process", expected[i].toString(),result[i].toString()), true, expected[i].equals(result[i]) );
                    }
                }
            } else {
                fail(String.format("No interval found, expected %d", expected.length));
            }

            IInterval[] waitings = proc.getWaitIntervals();
            if(waitings != null) {
                if (expectedWaiting.length != waitings.length) {
                    assertEquals(String.format("Should be has %d waitings interval", expectedWaiting.length), expectedWaiting.length, waitings.length );
                } else {
                    for( int i = 0; i < expectedWaiting.length;i+=1) {
                        assertEquals(String.format("Should be waitings interval expected %s equals to result %s in run process", expectedWaiting[i].toString(),waitings[i].toString()), true, expectedWaiting[i].equals(waitings[i]) );
                    }
                }
            } else {
                fail(String.format("No wainting interval found, expected %d", expectedWaiting.length));
            }
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }
    }

    @Test
    public void testIntervalsWithIO() {
        Time clock = new Time(0);
        IContext c = new Context(null, 3, clock);
        IInterval[] expected = new Interval[4];
        IInterval[] expectedWaiting = new Interval[3];

        try {
            Process proc = new Process(c, 1, 10, 0);
            proc.addIOTimes(2);
            proc.addIOTimes(5);

            expected[3] = new Interval(1,2);
            expected[2] = new Interval(3,5);
            expected[1] = new Interval(6,8);
            expected[0] = new Interval(9,10);

            expectedWaiting[2] = new Interval(2,3);
            expectedWaiting[1] = new Interval(5,6);
            expectedWaiting[0] = new Interval(8,9);

            for( int i = 0; i < proc.getDuration(); i+=1) {
                clock.add(1);
                proc.Run();
            }

            if(proc.isProcessing()) {
                proc.Stop();
            }

            IInterval[] result = proc.getIntervals();
            if(result != null) {
                if (expected.length != result.length) {
                    assertEquals(String.format("Should be %d interval", expected.length), expected.length, result.length );
                } else {
                    for( int i = 0; i < expected.length;i ++) {
                        assertEquals(String.format("Should be interval expected %s equals to result %s in run process", expected[i].toString(),result[i].toString()), true, expected[i].equals(result[i]) );
                    }
                }
            } else {
                fail(String.format("No interval found, expected %d", expected.length));
            }


            IInterval[] waitings = proc.getWaitIntervals();
            if(waitings != null) {
                if (expectedWaiting.length != waitings.length) {
                    assertEquals(String.format("Should be has %d waitings interval", expectedWaiting.length), expectedWaiting.length, waitings.length );
                } else {
                    for( int i = 0; i < expectedWaiting.length;i+=1) {
                        assertEquals(String.format("Should be waitings interval expected %s equals to result %s in run process", expectedWaiting[i].toString(),waitings[i].toString()), true, expectedWaiting[i].equals(waitings[i]) );
                    }
                }
            } else {
                fail(String.format("No wainting interval found, expected %d", expectedWaiting.length));
            }
        } catch (Exception e) {
            fail("Unexpected exception : " + e);
        }
    }
}
