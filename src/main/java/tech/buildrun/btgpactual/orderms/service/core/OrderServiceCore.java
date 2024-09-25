package tech.buildrun.btgpactual.orderms.service.core;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

import tech.buildrun.btgpactual.orderms.controller.dto_controller.OrderResponse;
import tech.buildrun.btgpactual.orderms.dto.OrderCreatedEvent;
import tech.buildrun.btgpactual.orderms.model.OrderEntity;
import tech.buildrun.btgpactual.orderms.model.OrderItem;
import tech.buildrun.btgpactual.orderms.repository.OrderRepository;
import tech.buildrun.btgpactual.orderms.service.OrderService;

@Service
public class OrderServiceCore implements OrderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(OrderCreatedEvent event) {
        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        

        entity.setItems(saveItems(event));
        entity.setAmount(calculateAmount(entity.getItems()));

        orderRepository.save(entity);
    }

    @Override
    public BigDecimal findTotalOnOrdersByCustomerId(long customerId) {
       // Build the aggregation pipeline
    Aggregation aggregation = newAggregation(
        match(Criteria.where("customerId").is(customerId)),
        group().sum("amount").as("total")
    );
    AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, "tb_orders", Document.class);

       // Extract the total amount from the result
    Document document = result.getUniqueMappedResult();
    if (document != null && document.get("total") != null) {
        return new BigDecimal(document.get("total").toString());
    } else {
        return BigDecimal.ZERO;
    }

    }

    @Override
    public Page<OrderResponse> findAllByCustomerId(long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponse::fromEntity);
    }

    private BigDecimal calculateAmount(List<OrderItem> items) {
        return items.stream().map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    private List<OrderItem> saveItems(OrderCreatedEvent event) {
        return event.itens().stream().map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco())).toList();
    }
    
}
