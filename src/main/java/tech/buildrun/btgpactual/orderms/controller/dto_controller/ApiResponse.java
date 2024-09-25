package tech.buildrun.btgpactual.orderms.controller.dto_controller;

import java.util.List;
import java.util.Map;

public record ApiResponse<T>( Map<String, Object> Summary, List<T> data, PaginationResponse pagination) {

    
}
