package tech.buildrun.btgpactual.orderms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tech.buildrun.btgpactual.orderms.controller.dto_controller.ApiResponse;
import tech.buildrun.btgpactual.orderms.controller.dto_controller.OrderResponse;
import tech.buildrun.btgpactual.orderms.controller.dto_controller.PaginationResponse;
import tech.buildrun.btgpactual.orderms.service.OrderService;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrdersByCustomer(@PathVariable("customerId") Long customerId,
                                                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){    
        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));     
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId); 
                
        return ResponseEntity.ok( new ApiResponse<>(Map.of("totalOnOrders", totalOnOrders), pageResponse.getContent(), PaginationResponse.fromPage(pageResponse) ) );
    }

    
    
}
