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
 * Created by Student on 01-04-19.
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
}
