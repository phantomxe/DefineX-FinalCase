package com.patika.notificationservice.operations;

import com.patika.notificationservice.entity.ITransfer;

public class SmsOperation implements ITransfer {
    private final String senderName = "KredinBizden";

    @Override
    public void Send(String message) {
        System.out.println("SMS sent by " + senderName);
    }
}
