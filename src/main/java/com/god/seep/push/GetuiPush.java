package com.god.seep.push;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetuiPush implements IPush {
    private static String appId = "xxx";
    private static String appKey = "xxx";
    private static String masterSecret = "xxx";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    IGtPush gtPush;
    private int count;

    public GetuiPush() {
        gtPush = new IGtPush(appKey, masterSecret);
    }

    @Override
    public void pushAllDevice() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
//        String content = String.format("后台服务于%s 主动发出推送测试", dateStr);
        String content = String.format("{\"send\":\"%s\",\"sendTime\":%s,\"messageId\":%d}", dateStr, date.getTime(), ++count);

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("GeTui后台推送");
        template.setText(content);
        template.setUrl("http://getui.com");

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        List<String> ids = new ArrayList<>();
        ids.add(appId);
        message.setAppIdList(ids);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult pushResult = gtPush.pushMessageToApp(message);

        System.out.println("个推推送结果：" + pushResult.getResponse().toString());
    }

    @Override
    public void queryPushStatus() {

    }

    @Override
    public void queryDeviceCount() {

    }
}
