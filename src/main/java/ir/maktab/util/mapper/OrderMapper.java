package ir.maktab.util.mapper;

import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.entity.order.Order;

/**
 * @author Negin Mousavi
 */
public class OrderMapper {
    private static final int suffix = 1000;

    public static Order mapOrderDtoToOrder(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getIdentity() - suffix)
                .address(AddressMapper.mapAddressDtoToAddress(orderDto.getAddress()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(orderDto.getCustomer()))
                .description(orderDto.getDescription())
                .orderStatus(orderDto.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceDtoToSubService(orderDto.getSubService()))
                .finalPrice(orderDto.getFinalPrice())
                .expert(ExpertMapper.mapExpertDtoToExpert(orderDto.getExpert()))
                .registrationDate(orderDto.getRegistrationDate())
                .toBeDoneDate(orderDto.getToBeDoneDate())
//                .suggestions() todo --> ????
                .build();
    }

    public static Order mapOrderDtoToOrderForSaving(OrderDto orderDto) {
        return Order.builder()
                .address(AddressMapper.mapAddressDtoToAddressForSaving(orderDto.getAddress()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(orderDto.getCustomer()))
                .description(orderDto.getDescription())
                .orderStatus(orderDto.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceDtoToSubService(orderDto.getSubService()))
                .build();
    }

    public static OrderDto mapOrderToOrderDto(Order order) {
        return OrderDto.builder()
                .identity(order.getId() + suffix)
                .address(AddressMapper.mapAddressToAddressDto(order.getAddress()))
                .customer(CustomerMapper.mapCustomerToCustomerDto(order.getCustomer()))
                .description(order.getDescription())
                .orderStatus(order.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceToSubServiceDto(order.getSubService()))
                .finalPrice(order.getFinalPrice())
                .expert(ExpertMapper.mapExpertToExpertDto(order.getExpert()))
                .registrationDate(order.getRegistrationDate())
                .toBeDoneDate(order.getToBeDoneDate())
//                .suggestions() todo --> ????
                .build();
    }
}
