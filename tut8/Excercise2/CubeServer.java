package tut8.Excercise2;

import java.net.*;

public class CubeServer {
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Cube Server is running...");

            while (true) {
                // Receive data from client
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);
                
                // Parse the received data
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                double number = Double.parseDouble(receivedData.trim());
                
                // Calculate the cube
                double cube = Math.pow(number, 3);
                String response = Double.toString(cube);

                // Send the response back to the client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] responseData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
