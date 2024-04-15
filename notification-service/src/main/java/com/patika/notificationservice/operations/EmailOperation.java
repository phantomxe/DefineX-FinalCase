package com.patika.notificationservice.operations;

import com.patika.notificationservice.entity.ITransfer;

public class EmailOperation implements ITransfer {
    private final String emailProvider = "GMail";

    @Override
    public void Send(String message) {
        System.out.println("Email sent by " + emailProvider);
    }
}
