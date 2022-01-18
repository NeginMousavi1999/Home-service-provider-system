package ir.maktab.service.implementation;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.data.entity.order.Address;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.repository.OrderRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.AddressService;
import ir.maktab.service.OrderService;
import ir.maktab.util.mapper.CustomerMapper;
import ir.maktab.util.mapper.OrderMapper;
import ir.maktab.util.mapper.SubServiceMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressService addressService;

    public void update(OrderDto orderDto) {
        orderRepository.save(OrderMapper.mapOrderDtoToOrderForSaving(orderDto));
    }

    public boolean saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapOrderDtoToOrderForSaving(orderDto);
/*        addressService.save(orderDto.getAddress());*/
        orderRepository.save(order);
        return true;
    }

    public OrderDto findById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty())
            throw new HomeServiceException("we have not order with this id!");
        return OrderMapper.mapOrderToOrderDto(order.get());
    }

    public List<OrderDto> findBySubService(SubServiceDto subServiceDto) {
        Optional<List<Order>> orders = orderRepository.findBySubService(SubServiceMapper.mapSubServiceDtoToSubService(subServiceDto));
        if (orders.isEmpty())
            throw new HomeServiceException("we have not order with this sub service!");
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDto).collect(Collectors.toList());
    }

    public Set<OrderDto> getOrdersByCustomer(CustomerDto customerDto) {
        Optional<List<Order>> orders = orderRepository.findByCustomer(CustomerMapper.mapCustomerDtoToCustomer(customerDto));
        if (orders.isEmpty())
            throw new HomeServiceException("we have not order with this customer!");
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDto).collect(Collectors.toSet());
    }

    public List<OrderDto> getOrdersByCustomerAndStatus(CustomerDto customerDto, OrderStatus orderStatus) {
        Optional<List<Order>> orders = orderRepository.findByCustomerAndOrderStatus(CustomerMapper.
                mapCustomerDtoToCustomer(customerDto), orderStatus);
        if (orders.isEmpty())
            throw new HomeServiceException("we have not order with this conditions!");
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDto).collect(Collectors.toList());
    }
}
