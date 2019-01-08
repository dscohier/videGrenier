package be.icc.repository;

import be.icc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Student on 09-12-18.
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
}
