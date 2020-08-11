package com.god.seep.push;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XingePush implements IPush {
    private XingeApp xingeApp;
    private int count;

    public XingePush() {
        xingeApp = new XingeApp(121, "xxx");
    }

    @Override
    public void pushAllDevice() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        String format = String.format("后台服务于%s 主动发出推送测试", dateStr);
        String content = String.format("{\"send\":\"%s\",\"sendTime\":%s,\"messageId\":%d}", dateStr, date.getTime(), ++count);
//        JSONObject jsonObject = XingeApp.pushAllAndroid(2100269029, "f52d2573d426a2498a2674db293301ef",
//                "信鸽推送测试", format);
        Message message = new Message();
        message.setTitle("信鸽后台推送");
        message.setContent(content);
        message.setType(Message.TYPE_NOTIFICATION);
        JSONObject jsonObject = xingeApp.pushAllDevice(0, message);
        System.out.println("信鸽推送消息：" + format);
        System.out.println("信鸽推送测试结果：" + jsonObject.toString());
    }

    @Override
    public void queryPushStatus() {
        List<String> ids = new ArrayList<>();
        ids.add("3643079594");//推送成功返回的 push_id
        JSONObject jsonObject = xingeApp.queryPushStatus(ids);
        System.out.println("推送状态查询：" + jsonObject.toString());
    }

    @Override
    public void queryDeviceCount() {
        JSONObject jsonObject = xingeApp.queryDeviceCount();
        System.out.println("推送设备查询：" + jsonObject.toString());
    }
}
