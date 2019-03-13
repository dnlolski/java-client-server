package com.company;

import java.io.*;
import java.net.*;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //static ServerSocket variable
        ServerSocket server;
        //socket server port on which it will listen
        int port = 9876;
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            System.out.println("Client connected \n");
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            Integer number = (Integer) ois.readObject();
            System.out.println("Number received to square: " + number);
            //terminate the server if client sends exit request
            if(number.equals(0)){
                ois.close();
                socket.close();
                break;
            }
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Square of received number: " + number*number);
            //close resources
            ois.close();
            oos.close();
            socket.close();

        }
        System.out.println("Shutting down Socket server!");
        //close the ServerSocket object
        server.close();
    }
}
