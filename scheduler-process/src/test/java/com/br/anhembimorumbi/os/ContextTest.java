package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import static org.junit.Assert.*;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class ContextTest {
    @Test 
    public void testIncrementTime() {
        try{
            Time time = new Time(0);
            IContext  context = new Context(null, 0, time);

            time.set(5);
            assertEquals("Should value is 5", 5, context.getClock().get());
            time.set(35);
            assertEquals("Should value is 35", 35, context.getClock().get());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}
