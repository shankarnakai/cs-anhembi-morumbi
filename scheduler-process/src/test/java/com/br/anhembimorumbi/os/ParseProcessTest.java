package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.io.InputStream;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class ParseProcessTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public void testParseIO(String line, long[] expected) {
        try{
            long[] result = ParseProcess.parseIO(line);
            if(result == null && expected != null) {
                fail("Unexpected empty result to try ParseIO");
                return;
            } else if(result == null && expected == null) {
                return;
            }
            assertEquals("Should has size equals ", expected.length, result.length);
            assertArrayEquals("Should be the same", expected,result);
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test 
    public void testParseIOScenaries() {
        testParseIO("1,2,3,4", new long[]{1,2,3,4});
        testParseIO("", null);
    }

    @Test 
    public void testParseLine() {
        try{
            IProcess result = ParseProcess.parseLine("P1 9 10 2,4,6,8");
            IProcess expected = new Process(1, 9, 10);
            expected.addIOTimes(2);
            expected.addIOTimes(4);
            expected.addIOTimes(6);
            expected.addIOTimes(8);

            if(result == null) {
                fail("Unexpected empty result to try parseLine");
                return;
            }
            assertEquals("Should be the same", true,expected.equals(result));
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test
    public void testParseFile() {
        InputStream in = getClass().getResourceAsStream("/file.proc");
        try{
            IProcessQueue result = ParseProcess.parse(in);
            
            IProcess[] items = new IProcess[5]; 
            items[0] = new Process(3, 5, 0);
            items[0].addIOTimes(2);

            items[1] = new Process(4, 7, 1); 
            items[1].addIOTimes(3);
            items[1].addIOTimes(6);

            items[2] = new Process(2, 10, 4);
            items[2].addIOTimes(5);

            items[3] = new Process(1, 9, 10);
            items[3].addIOTimes(2);
            items[3].addIOTimes(4);
            items[3].addIOTimes(6);
            items[3].addIOTimes(8);
            
            items[4] = new Process(5, 2, 17);
            
            if(result == null) {
                fail("Unexpected empty result to try parseLine");
                return;
            }

            IProcess[] resultArray = result.toArray();
            if(resultArray.length == items.length) {
                for(int i = 0; i < items.length; i += 1) {
                    assertEquals(String.format("Should be the same the expected \"%d\", found \"%d\"", items[i].getDuration(), resultArray[i].getDuration()), true, items[i].equals(resultArray[i]));
                    assertEquals(String.format("Should be the same the expected \"%s\", found \"%s\"", items[i].getPID(), resultArray[i].getPID()), true, items[i].equals(resultArray[i]));
                }
            } else {
                fail(String.format("Unexpected length %d, expected %d", resultArray.length, items.length));
            }
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}