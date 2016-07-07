package com.pffair;

import com.xiaomi.xmpush.server.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by password on 16-7-7.
 */
public class SendMessage {
    private static final String APP_SECRET_KEY="Tt8yejGZ/lP/BxGG+w270g==";
    private static final String MY_PACKAGE_NAME="com.pffair";
    private boolean isRelease=true;


    public String sendMessage() throws Exception {
        selectEnvironment();
        Sender sender = new Sender(APP_SECRET_KEY);
        String messagePayload= "This is a message";
        String title = "notification title";
        String description = "notification description";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result=sender.send(message, "", 0); //根据regID，发送消息到指定设备上，不重试。
        return printfResult(result);
    }



    public String sendMessages() throws Exception {
        selectEnvironment();
        Sender sender = new Sender(APP_SECRET_KEY);
        List<TargetedMessage> messages = new ArrayList<TargetedMessage>(10);
        TargetedMessage targetedMessage1 = new TargetedMessage();
        targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "lt");
        String messagePayload1= "This is a message1";
        String title1 = "notification title1";
        String description1 = "notification description1";
        Message message1 = new Message.Builder()
                .title(title1)
                .description(description1).payload(messagePayload1)
                .restrictedPackageName(MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        targetedMessage1.setMessage(message1);
        messages.add(targetedMessage1);
        TargetedMessage targetedMessage2 = new TargetedMessage();
        targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias2");
        String messagePayload2= "This is a message2";
        String title2 = "notification title2";
        String description2 = "notification description2";
        Message message2 = new Message.Builder()
                .title(title2)
                .description(description2).payload(messagePayload2)
                .restrictedPackageName(MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        targetedMessage2.setMessage(message2);
        messages.add(targetedMessage2);
        Result result=sender.send(messages, 0); //根据alias，发送消息到指定设备上，不重试。
        return printfResult(result);
    }

    public String sendMessageToAlias() throws Exception {
        selectEnvironment();
        Sender sender = new Sender(APP_SECRET_KEY);
        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        String alias = "lt";    //alias非空白，不能包含逗号，长度小于128。
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result=sender.sendToAlias(message, alias, 0); //根据alias，发送消息到指定设备上，不重试。
        return printfResult(result);
    }

    public String sendMessageToAliases() throws Exception {
        selectEnvironment();
        Sender sender = new Sender(APP_SECRET_KEY);
        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        List<String> aliasList = new ArrayList<String>(10);
        aliasList.add("lt");  //alias非空白，不能包含逗号，长度小于128。
        aliasList.add("testAlias2");  //alias非空白，不能包含逗号，长度小于128。
        aliasList.add("testAlias3");  //alias非空白，不能包含逗号，长度小于128。
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MY_PACKAGE_NAME)
                .passThrough(0)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result=sender.sendToAlias(message, aliasList, 0); //根据aliasList，发送消息到指定设备上，不重试。
        return printfResult(result);
    }

    public String sendBroadcast() throws Exception {
        selectEnvironment();
        Sender sender = new Sender(APP_SECRET_KEY);
        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        String topic = "testTopic";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result=sender.broadcast(message, topic, 0); //根据topic，发送消息到指定一组设备上，不重试。
        return printfResult(result);
    }

    /**
     * 选择环境
     */
    private void selectEnvironment() {
        if (isRelease) {
            Constants.useOfficial();
        }else {
            Constants.useSandbox();
        }
    }

    /**
     * 显示返回结果
     * @param result
     * @return
     */
    private String printfResult(Result result){
       return  "Server response: "+"MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().getDescription()
                + " Reason: " + result.getReason();
    }
}
