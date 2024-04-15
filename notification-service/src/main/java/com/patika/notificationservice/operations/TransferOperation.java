package com.patika.notificationservice.operations;

import com.patika.notificationservice.entity.ITransfer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j; 

@Slf4j
@AllArgsConstructor
public class TransferOperation {
    private ITransfer transfer;

    public void Send(String message) {
        transfer.Send(message);
        log.info("message sent successfully.");
    }
}
