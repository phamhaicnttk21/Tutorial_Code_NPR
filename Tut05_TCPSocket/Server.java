package Tut05_TCPSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author haipham
 * 17/11/2024
 */

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started and waiting for client connection...");

        try (Socket socket = serverSocket.accept();
             DataInputStream inputNumber = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Client connected.");

            // Receive number from client
            int input = inputNumber.readInt();
            System.out.println("Received number: " + input);

            // Calculate square
            int result = input * input;
            System.out.println("Calculated square: " + result);

            // Send result back to client
            dataOutputStream.writeInt(result);
            System.out.println("Result sent to client.");
        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        } finally {
            serverSocket.close();
        }
    }
}
