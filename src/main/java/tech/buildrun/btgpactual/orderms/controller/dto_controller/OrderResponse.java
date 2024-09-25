package tech.buildrun.btgpactual.orderms.controller.dto_controller;

import java.math.BigDecimal;

import tech.buildrun.btgpactual.orderms.model.OrderEntity;

public record OrderResponse(Long OrderId, Long customerID, BigDecimal total ) {


    public static OrderResponse fromEntity(OrderEntity entity) {
        return new OrderResponse(entity.getOrderId(), entity.getCustomerId(), entity.getAmount());
    }
}
