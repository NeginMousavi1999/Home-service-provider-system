package ir.maktab.service;

import ir.maktab.dao.OrderDao;
import ir.maktab.model.order.Order;
import ir.maktab.model.services.SubService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class OrderService {
    private final OrderDao orderDao;

    public void update(Order order) {
        orderDao.update(order);
    }

    public boolean saveOrder(Order order) {
        orderDao.create(order);
        return true;
    }

    public Order findById(int id) {
        return orderDao.findById(id);
    }

    public List<Order> findBySubService(SubService subService) {
        return orderDao.findBySubService(subService);
    }
}
