package com.god.seep.push;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JGPush implements IPush {
    private org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger("JiGuang");
    private static final String masterSecret = "4b6356eace4c57ee7790713b";
    private static final String appKey = "d318fa573aa250ec6c956767";

    private JPushClient jpushClient;
    private int count;

    JGPush() {
        jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
    }

    @Override
    public void pushAllDevice() {
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert();
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("极光推送结果：Got result - " + result);
            LOG.error("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    private PushPayload buildPushObject_all_all_alert() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        String content = String.format("{\"send\":\"%s\",\"sendTime\":%s,\"messageId\":%d}", dateStr, date.getTime(), ++count);
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(content, "极光后台推送", null))
                .build();
    }

    @Override
    public void queryPushStatus() {

    }

    @Override
    public void queryDeviceCount() {

    }
}
