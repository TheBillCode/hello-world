package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    final static int PORT = 9876; 
    final static String IP_LOCATION = "thebillcode.ddns.net"; //for dynamic IP
    static Scanner clientInput = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String str; //for temporary string storage
        Socket serverSocket = new Socket(IP_LOCATION, PORT); //request connection

        //object 'pr' to send string to server
        PrintWriter pr = new PrintWriter(serverSocket.getOutputStream(), true);
        
        //object 'in' points to server stream
        InputStreamReader in = new InputStreamReader(serverSocket.getInputStream());
        
        //object 'bf' stores entire server string
        BufferedReader bf = new BufferedReader(in);
        str = bf.readLine(); //read one line from 'bf'
        System.out.println(str); 
        str = bf.readLine(); //read one line from 'bf'
        System.out.println(str);
        while (serverSocket.isConnected()) { //while connected to server
            System.out.print("Client: ");
            String message = clientInput.nextLine(); //get user input
            pr.println(message); //send message to server

            str = bf.readLine(); //read one line from 'bf'
            if (str != null & str.length() >= 1) {
                if (str.split(" ")[0].equals("0102")) { //connection terminated
                    System.out.println(str.substring(5));
                    break;

                } else if (str.split(" ")[0].equals("0101")) { //multi-line start
                    while (str != null) {
                        str = bf.readLine(); //read one line from 'bf'
                        if (str.equals("0103")) { //end multi line
                            str = "";
                            break;
                        }
                        System.out.println(str);
                    }
                } else {
                    System.out.println(str);
                }
            }
        } //end connection
    } //end main
} //end client class
