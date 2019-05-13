package be.icc.repository;

import be.icc.dto.UserDto;
import be.icc.entity.Category;
import be.icc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Scohier Dorian on 09-12-18.
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

   List<Product> findByCategoryAndIsSellFalseAndEndDateAfterOrEndDateIsNullOrderByCreationDateDesc(Category category, Date date);

   List<Product> findByIsSellFalseAndEndDateAfterOrEndDateIsNullOrderByCreationDateDesc(Date date);

    List<Product> findBySellerOrderByCreationDateDesc(UserDto seller);

    List<Product> findByCategoryIn(List<Category> category);
}
