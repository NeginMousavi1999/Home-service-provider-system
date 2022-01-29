package ir.maktab.service.implementation;

import ir.maktab.data.dto.*;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.data.repository.OrderRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.*;
import ir.maktab.util.mapper.CustomerMapper;
import ir.maktab.util.mapper.ExpertMapper;
import ir.maktab.util.mapper.OrderMapper;
import ir.maktab.util.mapper.SubServiceMapper;
import ir.maktab.validation.Validation;
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
    private final SubServiceService subServiceService;
    private final ServiceService serviceService;
    private final Validation validation;
    private final int suffix = 1000;

    public void updateStatus(OrderDto orderDto) {
        orderRepository.updateStatus(orderDto.getIdentity() - suffix, orderDto.getOrderStatus());
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapOrderDtoToOrderForSaving(orderDto);
        orderRepository.save(order);
    }

    public OrderDto findById(int id) {
        Optional<Order> order = orderRepository.findById(id - suffix);
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
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDtoToPay).collect(Collectors.toSet());
    }

    public List<OrderDto> getOrdersByCustomerAndStatus(CustomerDto customerDto, OrderStatus orderStatus) {
        Optional<List<Order>> orders = orderRepository.findByCustomerAndOrderStatus(CustomerMapper.
                mapCustomerDtoToCustomer(customerDto), orderStatus);
        if (orders.isEmpty())
            throw new HomeServiceException("we have not order with this conditions!");
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDtoToPay).collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersReadyForSuggestion(ExpertDto expertDto) {
        Set<SubService> subServices = expertDto.getSubServiceDtos().stream()
                .map(SubServiceMapper::mapSubServiceDtoToSubService).collect(Collectors.toSet());
        Optional<List<Order>> filteredOrders = orderRepository
                .findReadyOrdersForExpert(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION, subServices);
        if (filteredOrders.isEmpty())
            throw new HomeServiceException("no ready orders!");
        return filteredOrders.get().stream()
                .map(OrderMapper::mapOrderToOrderDtoForToBeSuggestioned).collect(Collectors.toList());
    }

    public void update(OrderDto order) {
        orderRepository.save(OrderMapper.mapOrderDtoToOrderWithoutSuggestion(order));
    }

    @Override
    public List<OrderDto> getOrdersToStartByExpert(ExpertDto expertDto) {
        Optional<List<Order>> orders = orderRepository.findByExpertAndOrderStatus(ExpertMapper.mapExpertDtoToExpert(expertDto)
                , OrderStatus.SPECIALIST_COMING_YOUR_PLACE);
        if (orders.isEmpty())
            throw new HomeServiceException("no order to start for you");
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDtoToStart).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersToFinishByExpert(ExpertDto expertDto) {
        Optional<List<Order>> orders = orderRepository.findByExpertAndOrderStatus(ExpertMapper.mapExpertDtoToExpert(expertDto)
                , OrderStatus.STARTED);
        if (orders.isEmpty())
            throw new HomeServiceException("no order to finish for you");
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDtoWithoutSuggestion).collect(Collectors.toList());
    }

    @Override
    public void finishOrder(OrderDto orderDto) {
        orderDto.setOrderStatus(OrderStatus.DONE);
        updateStatus(orderDto);
    }

    @Override
    public void startOrder(OrderDto orderDto) {
        orderDto.setOrderStatus(OrderStatus.STARTED);
        updateStatus(orderDto);
    }

    @Override
    public OrderDto addNewOrder(OrderRequestDto orderRequest, CustomerDto customerDto) {
        validation.validateUserStatus(UserStatus.CONFIRMED, customerDto.getUserStatus());
        AddressDto addressDto = AddressDto.builder()
                .country(orderRequest.getCountry())
                .city(orderRequest.getCity())
                .state(orderRequest.getState())
                .postalCode(orderRequest.getPostalCode())
                .build();
        SubServiceDto subServiceDto = subServiceService.findSubServiceByName(orderRequest.getSubServiceName());
        OrderDto orderDto = OrderDto.builder()
                .address(addressDto)
                .customer(customerDto)
                .description(orderRequest.getDescription())
                .orderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)
                .subService(subServiceDto)
                .build();
        saveOrder(orderDto);
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrdersGivenByCustomer() {
        List<Order> allOrders = (List<Order>) orderRepository.findAll();
        return allOrders.stream().map(OrderMapper::mapOrderToOrderDtoToPay).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersDoneByExpert() {
        Optional<List<Order>> orders = orderRepository.findByNotEqualsSatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION,
                OrderStatus.SPECIALIST_COMING_YOUR_PLACE);
        if (orders.isEmpty())
            throw new HomeServiceException("no services done by experts!");
        return orders.get().stream().map(OrderMapper::mapOrderToOrderDtoToPay).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> filteredOrders(OrdersHistoryDto conditions) {
        return orderRepository.findAll(OrderRepository.selectByConditions(conditions))
                .stream().map(OrderMapper::mapOrderToOrderDtoToPay).collect(Collectors.toList());
    }

    @Override
    public int findNumberOfRegisteredRequestsByCustomer(CustomerDto customer) {
        return orderRepository.countByCustomer(CustomerMapper.mapCustomerDtoToCustomer(customer));
    }

    @Override
    public int findNumberOfOrdersPlacedByExpert(ExpertDto expert) {
        return orderRepository.countByExpert(ExpertMapper.mapExpertDtoToExpert(expert));
    }
}
