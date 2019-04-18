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

public class OracleClientTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenStartClientThenHelloOracle() throws IOException {
        this.testServer("exit", Joiner.on(LN).join("Hello oracle", ""));
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        OracleClient client = new OracleClient(socket);
        client.start();
        assertThat(out.toString(), is(expected));
    }

}