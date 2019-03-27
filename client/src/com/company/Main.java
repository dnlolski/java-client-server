package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        Socket socket = new Socket("localhost", 1250);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        int number = 0;

        Scanner scanner = new Scanner(System.in);
        while(true){
            //establish socket connection to server
            //write to socket using ObjectOutputStream
            System.out.println("Sending request to Socket Server");
            System.out.println("Enter number to square: ");
            number = scanner.nextInt();
            if (number == 0) {
                oos.writeObject(number);
                break;
            }
            else {
                oos.writeObject(number);
            }
            //read the server response message

            String numberSquared = (String) ois.readObject();
            System.out.println("Message: " + numberSquared);
            //close resources
            Thread.sleep(100);
        }
        ois.close();
        oos.close();

    }
}
