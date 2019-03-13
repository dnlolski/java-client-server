package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        int number = 0;

        Scanner scanner = new Scanner(System.in);
        while(true){
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            System.out.println("Enter number to square: ");
            number = scanner.nextInt();
            if (number == 0) {
                oos.writeObject(number);
                oos.close();
                break;
            }
            else {
                oos.writeObject(number);
            }
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String numberSquared = (String) ois.readObject();
            System.out.println("Message: " + numberSquared);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
