package tech.buildrun.btgpactual.orderms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import tech.buildrun.btgpactual.orderms.controller.dto_controller.OrderResponse;
import tech.buildrun.btgpactual.orderms.dto.OrderCreatedEvent;
import java.math.BigDecimal;

public interface OrderService {

    public void saveOrder(OrderCreatedEvent orderCreatedEvent);
    
    public Page<OrderResponse> findAllByCustomerId(long customerId, PageRequest pageRequest);

    public BigDecimal findTotalOnOrdersByCustomerId(long customerId);
    
}
