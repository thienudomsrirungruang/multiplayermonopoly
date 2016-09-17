package com.thien.multiplayermonopoly;

import java.io.*;
import java.net.Socket;

public class MultiplayerMonopoly {
    private final int SERVERPORT = 41875;
    private final String SERVERIP = "255.255.255.255";
    private static MultiplayerMonopoly instance;
    public static void main(String[] args) throws IOException{
        instance = new MultiplayerMonopoly();
        Socket socket = new Socket(instance.getServerIP(), instance.getPort());
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        MessageReceiver receiver = new MessageReceiver(input, output, new UserInputGetter(new BufferedReader(new InputStreamReader(System.in))));
        receiver.start();
    }
    public int getPort(){
        return SERVERPORT;
    }
    public String getServerIP(){
        return SERVERIP;
    }
}