package be.icc.repository;

import be.icc.entity.Orders;
import be.icc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Scohier Dorian on 01-04-19.
 */
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByUser(User user);
    List<Orders> findByUserOrderByDate(User user);

    Orders findByProducts_Id(Long id);
}
