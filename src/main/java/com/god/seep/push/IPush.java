package com.god.seep.push;

public interface IPush {
    /**
     * 全量推送
     */
    void pushAllDevice();

    void queryPushStatus();

    void queryDeviceCount();
}
