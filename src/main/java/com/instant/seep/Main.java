package com.instant.seep;

import com.instant.seep.bean.UserInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public volatile static Map<Socket, UserInfo> mSockets = new HashMap<>();

    public static void main(String[] args) {
        // write your code here
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                mSockets.put(socket, new UserInfo(socket.getInetAddress().getHostName(), socket.getPort()));
                new HandleMessage(socket).start();
                print("connect-->" + socket.isConnected() + ",connected to remote " + socket.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(String msg) {
        System.out.println(msg);
    }

    public static String toJson() {

        return "";
    }
}
