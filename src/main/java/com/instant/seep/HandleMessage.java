package com.instant.seep
        ;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.instant.seep.bean.Entity;
import com.instant.seep.bean.FileInfo;
import com.instant.seep.bean.UserInfo;
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
                System.out.println("command-->" + line);
                String[] command = line.split("：");
                if (command.length > 1)
                    line = command[0];
                switch (line) {
                    case Command.FILE_LIST:
                        FileUtil.writeFileList(writer);
                        break;
                    case Command.SEND_FILE:
                        FileUtil.sendFile(mSocket.getOutputStream(), command[1]);
                        break;
                    case Command.REV_FILE:
                        FileInfo info = JSON.parseObject(command[1], new TypeReference<FileInfo>() {
                        }.getType());
                        Main.print(info.toString());
                        FileUtil.revFile(mSocket.getInputStream(), info);
                        break;
                    case Command.ALL_USER:
                        List<UserInfo> list = new ArrayList<>(Main.mSockets.values());
                        Entity<List<UserInfo>> entity = new Entity<>();
                        if (list.size() > 0) {
                            entity.setSuccess(true);
                            entity.setData(list);
                        } else {
                            entity.setSuccess(false);
                            entity.setError("no user");
                        }
                        Object json = JSON.toJSON(entity);
                        writer.println(json);
                        writer.flush();
                        Main.print("gson --> " + json);
                        break;
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
