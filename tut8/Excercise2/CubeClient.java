package tut8.Excercise2;

import java.net.*;
import java.util.Scanner;

public class CubeClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a real number: ");
            double number = scanner.nextDouble();
            
            // Send the number to the server
            String message = Double.toString(number);
            byte[] buffer = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(SERVER_ADDRESS), PORT);
            socket.send(sendPacket);

            // Receive the response from the server
            byte[] responseBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Display the result
            System.out.println("Cube of " + number + " is: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
