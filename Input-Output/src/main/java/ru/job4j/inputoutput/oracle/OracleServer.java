package ru.job4j.inputoutput.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OracleServer {

    private Socket socket;

    public OracleServer(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {

            String ask = "";

            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!("exit".equals(ask))) {
                    out.println("Sorry, I don't understand");
                    out.println();
                }
            } while (!("exit".equals(ask)));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new ServerSocket(5000).accept();
        OracleServer server = new OracleServer(socket);
        server.start();
    }
}
