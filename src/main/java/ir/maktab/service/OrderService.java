package ir.maktab.service;

import ir.maktab.dao.OrderDao;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.order.Order;
import ir.maktab.model.services.SubService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class OrderService {
    private final OrderDao orderDao;

    public void update(Order order) {
//        orderDao.update(order);
        orderDao.save(order);
    }

    public boolean saveOrder(Order order) {
        orderDao.save(order);
        return true;
    }

    public Order findById(int id) {
        Optional<Order> order = orderDao.findById(id);
        if (order.isEmpty())
            throw new HomeServiceException("we have not order with this id!");
        return order.get();
    }

    public List<Order> findBySubService(SubService subService) {
        return orderDao.findBySubService(subService);
    }
}
