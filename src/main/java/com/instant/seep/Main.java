package com.instant.seep;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Socket> mSockets = new ArrayList<>();

    public static void main(String[] args) {
        // write your code here
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                mSockets.add(socket);
                new HandleMessage(socket).start();
                System.out.println("connect-->" + socket.isConnected() + ",connected to " + socket.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean receiveFile(InputStream inputStream, String fileName) {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] bytes = new byte[2048];
            int read = inputStream.read(bytes);
            while (read != -1) {
                fos.write(bytes);
                read = inputStream.read(bytes);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String toJson() {

        return "";
    }
}
