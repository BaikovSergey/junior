package ru.job4j.inputoutput.oracle;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OracleServerTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskAnswerThenRandom() throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenAskHelloThenBackHello() throws IOException {
        this.testServer(Joiner.on(LN).join(
                "hello",
                "exit"),
                String.format("Hello, dear friend, I'm a oracle.%s%s", LN, LN));
    }

    @Test
    public void whenAskUnsupportedQuestionThenDontUnderstand() throws IOException {
        this.testServer(Joiner.on(LN).join(
                "djkqnwqe",
                "exit"),
                Joiner.on(LN).join(
                        "Sorry, I don't understand", "", ""));
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        OracleServer server = new OracleServer(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

}