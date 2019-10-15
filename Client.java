package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static Scanner clientInput = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String str;
        Socket clientSocket = new Socket("98.4.6.145", 9876);

        PrintWriter pr = new PrintWriter(clientSocket.getOutputStream(), true);

        InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        str = bf.readLine();
        System.out.println(str);
        str = bf.readLine();
        System.out.println(str);
        while (clientSocket.isConnected()) {
            System.out.print("Client: ");
            String message = clientInput.nextLine();
            pr.println(message);
            pr.flush();
            str = bf.readLine();
            if (str != null) {
                System.out.println(str);
            }

        }
    }
}
