package Tut05;

import java.io.*;
import java.net.Socket;

/**
 * @author Haipham
 * 17/11/2024
 */
public class ClientWithThreads {
    public static void main(String[] args) throws IOException {
        try (Socket clientSocket = new Socket("localhost", 8888);
             DataOutputStream sentNumber = new DataOutputStream(clientSocket.getOutputStream());
             DataInputStream receivingNumber = new DataInputStream(clientSocket.getInputStream());
             BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server on port " + clientSocket.getPort());

            // Input number from user
            System.out.print("Please enter a number: ");
            int number = Integer.parseInt(inputFromUser.readLine());

            // Send the number to the server
            sentNumber.writeInt(number);
            System.out.println("Sent number to server: " + number);

            // Receive result from the server
            int result = receivingNumber.readInt();
            System.out.println("The square is: " + result);
        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
}
