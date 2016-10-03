package com.dood.elastic.config;

import com.dood.elastic.receivers.SimpleReceiver;
import com.dood.elastic.utils.QueueConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FIgure out how to do the quenenames differently, there are 3 instances of the simple
 * queuename here
 */
@Configuration
public class RabbitConfig {

    /**
     * Create a standard AMQP queue.   This has to be a
     * top level bean for spring boot
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(QueueConstants.SIMPLE_QUEUE, false);
    }

    /**
     * Create a Topic exchange  This has to be a
     * top level bean for spring boot
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    /**
     * Bind the queue to the topic exchange, and define the behavior - This has to be a
     * top level bean for spring boot
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QueueConstants.SIMPLE_QUEUE);
    }

    /**
     * Create the container for the listenerAdapter bean
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QueueConstants.SIMPLE_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }


    @Bean
    SimpleReceiver receiver() {
        return new SimpleReceiver();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(SimpleReceiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
        MessageListenerAdapter adapter = new MessageListenerAdapter(receiver);
        adapter.setDefaultListenerMethod("receiveMessage");
        return adapter;
    }
}
