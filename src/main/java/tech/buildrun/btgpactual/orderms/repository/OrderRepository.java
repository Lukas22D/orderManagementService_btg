package tech.buildrun.btgpactual.orderms.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import tech.buildrun.btgpactual.orderms.model.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByCustomerId(long customerId, PageRequest pageRequest);
    
}
