package be.icc.service.imp;

import be.icc.dto.OrdersDto;
import be.icc.entity.Orders;
import be.icc.repository.OrderRepository;
import be.icc.repository.UserRepository;
import be.icc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scohier Dorian on 01-04-19.
 */
@Service
@Transactional
public class OrderServiceImp implements OrderService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrdersDto> findByUser(Long userId) {
        List<Orders> orders = orderRepository.findByUser(userRepository.findOne(userId));
        List<OrdersDto> ordersDto = new ArrayList<>();
        for (Orders order : orders) {
            ordersDto.add(order.toDto());
        }
        return ordersDto;
    }

    public List<OrdersDto> findByUserOrderByDate(Long userId) {
        List<Orders> orders = orderRepository.findByUserOrderByDate(userRepository.findOne(userId));
        List<OrdersDto> ordersDto = new ArrayList<>();
        for (Orders order : orders) {
            ordersDto.add(order.toDto());
        }
        return ordersDto;
    }

    @Override
    public OrdersDto add(OrdersDto OrdersDto) {
        Orders orders = OrdersDto.toEntity();
        OrdersDto ordersSaved = orderRepository.save(orders).toDto();
        return ordersSaved;
    }

    @Override
    public OrdersDto findByProducts_Id(Long id) {
        Orders orders = orderRepository.findByProducts_Id(id);
        if (orders != null) {
            return orders.toDto();
        }
        return null;
    }
}
