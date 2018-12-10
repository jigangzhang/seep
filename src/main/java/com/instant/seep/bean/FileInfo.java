package com.instant.seep.bean;

public class FileInfo {
    private String fileName;
    private long fileSize;
    private long modifyTime;

    public FileInfo(String fileName, long fileSize, long modifyTime) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.modifyTime = modifyTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }
}
