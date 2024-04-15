package com.patika.notificationservice.operations;

import com.patika.notificationservice.entity.ITransfer;

public class MobileOperation implements ITransfer {
    private final String pushNotificationProvider = "OneSignal";

    @Override
    public void Send(String message) {
        System.out.println("Push notification sent by " + pushNotificationProvider);
    }
}
