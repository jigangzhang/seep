package com.god.seep.push;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.push.model.v20160801.*;
import com.aliyuncs.utils.ParameterHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ALiPush implements IPush {
    
    private static final String accessKeyID = "xxx";
    private static final String accessKeySecret = "xxx";
  
    private static final long appKey = 213;

    private DefaultAcsClient client;
    private int count;

    public ALiPush() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyID, accessKeySecret);
        client = new DefaultAcsClient(profile);
    }

    @Override
    public void pushAllDevice() {
        PushRequest request = new PushRequest();
        request.setAppKey(appKey);// 推送目标
//        request.setTarget("DEVICE"); //推送目标: DEVICE:按设备推送 ALIAS : 按别名推送 ACCOUNT:按帐号推送  TAG:按标签推送; ALL: 广播推送
//        request.setTargetValue(deviceIds); //根据Target来设定，如Target=DEVICE, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        request.setTarget("ALL"); //推送目标: DEVICE:推送给设备; ACCOUNT:推送给指定帐号,TAG:推送给自定义标签; ALL: 推送给全部
        request.setTargetValue("ALL"); //根据Target来设定，如Target=DEVICE, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        request.setPushType("NOTICE"); // 消息类型 MESSAGE NOTICE
        request.setDeviceType("ALL"); // 设备类型 ANDROID iOS ALL.
        // 推送配置
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        String content = String.format("{\"send\":\"%s\",\"sendTime\":%s,\"messageId\":%d}", dateStr, date.getTime(), ++count);
        request.setTitle("阿里后台推送"); // 消息的标题
        request.setBody(content); // 消息的内容
        // 推送配置: iOS
        request.setIOSBadge(5); // iOS应用图标右上角角标
        request.setIOSMusic("default"); // iOS通知声音
        request.setIOSSubtitle("iOS10 subtitle");//iOS10通知副标题的内容
        request.setIOSNotificationCategory("iOS10 Notification Category");//指定iOS10通知Category
        request.setIOSMutableContent(true);//是否允许扩展iOS通知内容
        request.setIOSApnsEnv("DEV");//iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
        request.setIOSRemind(true); // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
        request.setIOSRemindBody("iOSRemindBody");//iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT && iOSRemind为true时有效
        request.setIOSExtParameters("{\"_ENV_\":\"DEV\",\"k2\":\"v2\"}"); //通知的扩展属性(注意 : 该参数要以json map的格式传入,否则会解析出错)
        // 推送配置: Android
        request.setAndroidNotifyType("BOTH");//通知的提醒方式 "VIBRATE" : 震动 "SOUND" : 声音 "BOTH" : 声音和震动 NONE : 静音
        request.setAndroidNotificationBarType(1);//通知栏自定义样式0-100
        request.setAndroidNotificationBarPriority(1);//通知栏自定义样式0-100
        request.setAndroidOpenType("URL"); //点击通知后动作 "APPLICATION" : 打开应用 "ACTIVITY" : 打开AndroidActivity "URL" : 打开URL "NONE" : 无跳转
        request.setAndroidOpenUrl("http://www.aliyun.com"); //Android收到推送后打开对应的url,仅当AndroidOpenType="URL"有效
        request.setAndroidActivity("com.alibaba.push2.demo.XiaoMiPushActivity"); // 设定通知打开的activity，仅当AndroidOpenType="Activity"有效
        request.setAndroidMusic("default"); // Android通知音乐
        request.setAndroidPopupActivity("com.test.push.chengcheng.alipush.MainActivity");//设置该参数后启动辅助弹窗功能, 此处指定通知点击后跳转的Activity（辅助弹窗的前提条件：1. 集成第三方辅助通道；2. StoreOffline参数设为true）
        request.setAndroidPopupTitle("阿里-系统通道");
        request.setAndroidPopupBody(content);
        request.setAndroidExtParameters("{\"type\":\"android\",\"value\":\"ty001\"}"); //设定通知的扩展属性。(注意 : 该参数要以 json map 的格式传入,否则会解析出错)
        // 推送控制
        Date pushDate = new Date(System.currentTimeMillis()); // 30秒之间的时间点, 也可以设置成你指定固定时间
        String pushTime = ParameterHelper.getISO8601Time(pushDate);
//        request.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)); // 12小时后消息失效, 不会再发送
        request.setExpireTime(expireTime);
        request.setStoreOffline(true); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
        PushResponse pushResponse = null;
        try {
            pushResponse = client.getAcsResponse(request);
            System.out.printf("阿里推送结果：RequestId: %s, MessageID: %s\n， String：%s\n",
                    pushResponse.getRequestId(), pushResponse.getMessageId(), request.getHttpContentString());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryPushStatus() {
        QueryUniqueDeviceStatRequest request = new QueryUniqueDeviceStatRequest();
        request.setAppKey(appKey);
        request.setGranularity("MONTH");
        request.setStartTime("2020-04-00T00:00:00Z");
        request.setEndTime("2020-04-14T00:00:00Z");
        try {
            QueryUniqueDeviceStatResponse response = client.getAcsResponse(request);
            System.out.println("查询设备-" + response.getAppDeviceStats().toString());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryDeviceCount() {
        QueryDeviceStatRequest statRequest = new QueryDeviceStatRequest();
        statRequest.setAppKey(appKey);
        statRequest.setDeviceType("ALL");
        statRequest.setQueryType("TOTAL");
        statRequest.setStartTime("2020-03-28T00:00:00Z");
        statRequest.setEndTime("2020-04-13T14:35:00Z");
        try {
            QueryDeviceStatResponse response = client.getAcsResponse(statRequest);
            System.out.println("查询设备-" + response.getAppDeviceStats().toString());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public void checkDevices() {
        CheckDeviceRequest request = new CheckDeviceRequest();
        request.setAppKey(appKey);
        request.setDeviceId("ae296f3b04a58a05b30c95f13r4t");
        try {
            CheckDeviceResponse response = client.getAcsResponse(request);
            System.out.println("查询设备-" + response.getAvailable());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ALiPush push = new ALiPush();
        push.pushAllDevice();
        push.queryDeviceCount();
        push.checkDevices();
        push.queryPushStatus();
    }
}
