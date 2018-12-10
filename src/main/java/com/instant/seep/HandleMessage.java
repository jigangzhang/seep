package com.instant.seep;

import com.alibaba.fastjson.JSON;
import com.instant.seep.bean.Entity;
import com.instant.seep.bean.FileInfo;
import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
                if (line.equals(Constants.FILE_LIST)) {
                    File file = new File("H:\\shared");
                    List<FileInfo> list = new ArrayList<>();
                    if (file.exists() && file.isDirectory()) {
                        File[] files = file.listFiles();
                        if (files != null) {
                            for (File item : files) {
                                FileInfo info = new FileInfo(item.getName(), item.length(), item.lastModified());
                                list.add(info);
                            }
                        }
                    }
                    Entity<List<FileInfo>> entity = new Entity<>();
                    if (list.size() > 0) {
                        entity.setSuccess(true);
                        entity.setData(list);
                    } else {
                        entity.setSuccess(false);
                        entity.setError("no files");
                    }
                    writer.println(JSON.toJSON(entity));
                } else {
                    System.out.println("receive-->" + line);
                    writer.println("okk");
                    writer.flush();
                    line = reader.readLine();
                }
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
