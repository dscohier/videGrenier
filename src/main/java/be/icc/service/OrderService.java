package be.icc.service;

import be.icc.dto.OrdersDto;

import java.util.List;

/**
 * Created by Student on 01-04-19.
 */
public interface OrderService {

    List<OrdersDto> findByUser(Long userId);
}
