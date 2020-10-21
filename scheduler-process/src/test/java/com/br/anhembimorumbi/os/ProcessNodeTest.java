package com.br.anhembimorumbi.os;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import static org.junit.Assert.*;

/*
* @author Shankar Nakai Gonçalves dos Santos
*/
public class ProcessNodeTest {
    @Test 
    public void testSetValues() {
        IContext c = new Context(null);
        try{
            IProcess process = new Process(5, 10);
            IProcess process2 = new Process(6, 8);

            IProcessNode node = new ProcessNode(process);
            assertEquals(String.format("Should value is %s", process.getPID()), process.getPID(), node.getValue().getPID());
            node.setValue(process2);
            assertEquals(String.format("Should value is %s", process2.getPID()),process2.getPID(), node.getValue().getPID());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }

    @Test 
    public void testTimeSetNext() {
        IContext c = new Context(null);
        try{
            IProcess process = new Process(5, 10);
            IProcess process2 = new Process(6, 8);

            IProcessNode node = new ProcessNode(process);
            node.setNext(new ProcessNode(process2));

            assertEquals(String.format("Should value is %s", process.getPID()), process.getPID(), node.getValue().getPID());
            assertEquals(String.format("Should next value is %s", process2.getPID()),process2.getPID(), node.getNext().getValue().getPID());
        } catch(Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}