package com.instant.seep;

import com.alibaba.fastjson.JSON;
import com.instant.seep.bean.Entity;
import com.instant.seep.bean.FileInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void writeFileList(PrintWriter writer) {
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
        Object json = JSON.toJSON(entity);
        writer.println(json);
        writer.flush();
        System.out.println("json-->" + json);
    }

    public static boolean sendFile(OutputStream outputStream, String fileName) {
        File file = new File("H:\\shared\\" + fileName);
        if (!file.exists())
            return false;
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[2048];
            int read;
            while ((read = fis.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            fis.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
