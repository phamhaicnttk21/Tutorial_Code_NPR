package tut8.Excercise1;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String args[]) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader (System.in));
        int port = 9876;
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        //byte[] sendData = new byte[1024];
        //byte[] receiveData = new byte[1024];

        while(true) {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            System.out.print("Please enter your message: ");
            String sentence = inFromUser.readLine();
            System.out.println();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket (sendData,  sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket  (receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            modifiedSentence = modifiedSentence.trim();
            System.out.println("FROM SERVER:" + modifiedSentence);

            //clientSocket.close();
        }

    }
}