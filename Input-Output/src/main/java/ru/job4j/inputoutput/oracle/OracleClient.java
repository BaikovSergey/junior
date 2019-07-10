package ru.job4j.inputoutput.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class OracleClient {

    private Socket socket;

    public OracleClient(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Scanner console = new Scanner(System.in);

            do {
                out.println("Hello oracle");
                String str = in.readLine();
                while (!str.isEmpty()) {
                    System.out.println(str);
                }
            } while ("exit".equals(console.nextLine()));

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5000);
        OracleClient client = new OracleClient(socket);
        client.start();
    }
}
