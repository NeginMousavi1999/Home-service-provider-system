package ir.maktab.service.implementation;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.PaymentDto;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.PaymentMethod;
import ir.maktab.data.repository.PaymentRepository;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.service.PaymentService;
import ir.maktab.util.mapper.PaymentMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@Service
@RequiredArgsConstructor
@Getter
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @Override
    public CustomerDto pay(PaymentDto paymentDto) {
        assert paymentDto != null;
        OrderDto doneOrder = paymentDto.getOrder();
        double price = doneOrder.getFinalPrice();
        CustomerDto customerDto = doneOrder.getCustomer();
        if (paymentDto.getPaymentMethod().equals(PaymentMethod.CREDIT))
            customerDto = customerService.payByCredit(customerDto, price);

        ExpertDto expertDto = doneOrder.getExpert();
        double expertFees = price * 0.7;
        expertDto.setCredit(expertDto.getCredit() + expertFees);
        doneOrder.setOrderStatus(OrderStatus.PAID);
        expertService.update(expertDto);
        orderService.updateStatus(doneOrder);
        paymentRepository.save(PaymentMapper.mapPaymentDtoToPaymentForSaving(paymentDto));
        return customerDto;
    }
}
