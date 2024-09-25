package tech.buildrun.btgpactual.orderms.listener;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import tech.buildrun.btgpactual.orderms.dto.OrderCreatedEvent;
import tech.buildrun.btgpactual.orderms.service.OrderService;

import static tech.buildrun.btgpactual.orderms.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){
        logger.info("Order created event received: {}", message);

        orderService.saveOrder(message.getPayload());

    }

    
    
}
