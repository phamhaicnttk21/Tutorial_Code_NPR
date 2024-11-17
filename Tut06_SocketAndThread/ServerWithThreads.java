package Tut05;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Haipham
 * 17/11/2024
 */
public class ServerWithThreads {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started and waiting for client connections...");

        while (true) {
            // Accept a client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Create a new thread to handle the client
            Thread clientHandler = new Thread(new ClientHandler(socket));
            clientHandler.start();
        }
    }
}

// Runnable class to handle each client
class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream inputNumber = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            // Receive number from client
            int input = inputNumber.readInt();
            System.out.println("Received number from client: " + input);

            // Calculate square
            int result = input * input;
            System.out.println("Calculated square: " + result);

            // Send result back to client
            dataOutputStream.writeInt(result);
            System.out.println("Result sent to client.");

        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
