package com.thien.multiplayermonopoly;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageReceiverTest {
    @Test
    public void checkIfArrayContainsChar(){
        UserInputGetter uig = new UserInputGetter(new BufferedReader(new InputStreamReader(System.in)));
        String[] array = {"ewinvh", "aBc", "Psien", "qwojng"};
        assertTrue(uig.containsIgnoreCase("abc", array));
    }
    @Test
    public void tryReceivingSimpleAnswerMessages() throws IOException{
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        UserInputGetter uig = new UserInputGetter(new BufferedReader(new StringReader("Test Answer")));
        MessageReceiver mr = new MessageReceiver(new BufferedReader(new StringReader("")), pw, uig);
        mr.getAndProcessMessage("Request~GetInput~Test Message");
        String output = sw.toString();
        assertEquals("Test Answer", output);
    }
    @Test
    public void tryReceivingChoiceAnswerMessages() throws IOException{
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        BufferedReader input = Mockito.mock(BufferedReader.class);
        Mockito.when(input.readLine()).thenReturn("c", "a");
        UserInputGetter uig = new UserInputGetter(input);
        MessageReceiver mr = new MessageReceiver(new BufferedReader(new StringReader("")), pw, uig);
        mr.getAndProcessMessage("Request~MakeChoice~false~Test Message~a~b");
        String output = sw.toString();
        assertEquals("a", output);
    }
    @Test
    public void tryReceivingChoiceAnswerMessagesIgnoreCase() throws IOException{
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        BufferedReader input = Mockito.mock(BufferedReader.class);
        Mockito.when(input.readLine()).thenReturn("c", "A");
        UserInputGetter uig = new UserInputGetter(input);
        MessageReceiver mr = new MessageReceiver(new BufferedReader(new StringReader("")), pw, uig);
        mr.getAndProcessMessage("Request~MakeChoice~true~Test Message~a~b");
        String output = sw.toString();
        assertEquals("A", output);
    }
}