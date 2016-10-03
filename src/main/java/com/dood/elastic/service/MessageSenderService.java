package com.dood.elastic.service;

import com.dood.elastic.utils.QueueConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {
    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;

    public MessageSenderService(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    public void sendSimpleQueueMesssage(String simpleMessage) {
        rabbitTemplate.convertAndSend(QueueConstants.SIMPLE_QUEUE, simpleMessage);
    }
}
