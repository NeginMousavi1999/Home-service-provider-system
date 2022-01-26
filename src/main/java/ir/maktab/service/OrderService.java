package ir.maktab.service;

import ir.maktab.data.dto.*;
import ir.maktab.data.enumuration.OrderStatus;

import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */

public interface OrderService {

    void updateStatus(OrderDto order);

    boolean saveOrder(OrderDto order);

    OrderDto findById(int id);

    List<OrderDto> findBySubService(SubServiceDto subService);

    Set<OrderDto> getOrdersByCustomer(CustomerDto customer);

    List<OrderDto> getOrdersByCustomerAndStatus(CustomerDto customer, OrderStatus orderStatus);

    List<OrderDto> getOrdersReadyForSuggestion(ExpertDto expertDto);

    void update(OrderDto order);

    List<OrderDto> getOrdersToStartByExpert(ExpertDto expertDto);

    List<OrderDto> getOrdersToFinishByExpert(ExpertDto expertDto);

    void finishOrder(OrderDto orderDto);

    void startOrder(OrderDto orderDto);

    OrderDto addNewOrder(OrderRequestDto orderRequest, CustomerDto customerDto);
}
