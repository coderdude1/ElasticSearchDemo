package com.dood.elastic.controller;

import com.dood.elastic.service.MessageSenderService;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Easy way to send messages to the AMQP exchange")

@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    private MessageSenderService messageSenderService;

    public MessageController(@Autowired MessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
    }

    @RequestMapping(value = "/sendSimpleQueue", method = RequestMethod.POST)
    public void sendSimpleTextMessageQueue(@RequestParam String message) {
        messageSenderService.sendSimpleQueueMesssage(message);
    }
}
