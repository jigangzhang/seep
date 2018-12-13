package com.instant.seep
        ;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.Socket;

public class HandleMessage extends Thread {
    private Socket mSocket;

    public HandleMessage(@NotNull Socket socket) {
        mSocket = socket;
    }

    @Override
    public void run() {
        super.run();
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(mSocket.getOutputStream()));
            String line = reader.readLine();
            while (line != null && !line.equals("quit")) {
                System.out.println("command-->" + line);
                if (line.equals(Command.FILE_LIST)) {
                    FileUtil.writeFileList(writer);
                } else if (line.startsWith(Command.SEND_FILE)) {
                    FileUtil.sendFile(mSocket.getOutputStream(), line.replace(Command.SEND_FILE, ""));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (writer != null) {
                    writer.write("disconnect--");
                    writer.flush();
                    writer.close();
                }
                Main.mSockets.remove(mSocket);
                System.out.println(mSocket.getRemoteSocketAddress() + "<--disconnect");
                mSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
