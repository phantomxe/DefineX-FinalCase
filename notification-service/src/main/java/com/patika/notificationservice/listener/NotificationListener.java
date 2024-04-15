package com.patika.notificationservice.listener;

import com.patika.notificationservice.dto.NotificationDTO;
import com.patika.notificationservice.operations.EmailOperation;
import com.patika.notificationservice.operations.MobileOperation;
import com.patika.notificationservice.operations.SmsOperation;
import com.patika.notificationservice.operations.TransferOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void sendNotification(NotificationDTO notificationDTO) {

        log.info("kuyruktan okudun: {}", notificationDTO);

        TransferOperation myop = null;

        switch (notificationDTO.getNotificationType()) {
            case EMAIL:
                myop = new TransferOperation(new EmailOperation());
                break;
            case SMS:
                myop = new TransferOperation(new SmsOperation());
                break;
            case MOBILE_NOTIFICATION:
                myop = new TransferOperation(new MobileOperation());
                break;
            default:
                break;
        }

        if(myop != null) {
            myop.Send(notificationDTO.getMessage()); 
        } 

    }


}
