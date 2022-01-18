package ir.maktab.service;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.OrderStatus;

import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */

public interface OrderService {

    void update(OrderDto order);

    boolean saveOrder(OrderDto order);

    OrderDto findById(int id);

    List<OrderDto> findBySubService(SubServiceDto subService);

    Set<OrderDto> getOrdersByCustomer(CustomerDto customer);

    List<OrderDto> getOrdersByCustomerAndStatus(CustomerDto customer, OrderStatus orderStatus);
}
