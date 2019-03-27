package com.company;

import java.io.*;
import java.net.*;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException{

        int port = 1250;

        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        while(true){
            System.out.println("Waiting for the client request");

            System.out.println("Client connected \n");
            Integer number = (Integer) ois.readObject();
            System.out.println("Number received to square: " + number);
            //terminate the server if client sends 0
            if(number.equals(0)){
                break;
            }

            oos.writeObject("Square of received number: " + number*number);

        }
        System.out.println("Shutting down Socket server!");
        //close the ServerSocket object
        ois.close();
        socket.close();
        server.close();
    }
}
